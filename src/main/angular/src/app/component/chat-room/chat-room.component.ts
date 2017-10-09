import { HttpClient } from '@angular/common/http';
import { Component, OnInit, AfterContentInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { Message } from '../../domain/message';
import { User } from './../../domain/user';
import { WebsocketService } from '../../service/websocket.service';

const WEB_QQ_PUSH = '/webqq/chat';
const WEB_QQ_RECENT_MESSAGE = '/webqq/recent/message';

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css']
})
export class ChatRoomComponent implements OnInit, AfterContentInit {

  friend: string;
  messages: Message[] = new Array<Message>();
  currentMessage: Message = new Message();

  constructor(
    private route: ActivatedRoute,
    private Location: Location,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.friend = params.get('username'))
      .subscribe();
      const body: User = new User();
      body.username = this.friend;
      this.http
        .post(WEB_QQ_RECENT_MESSAGE, body)
        .subscribe(
          data => {
            this.messages = data['data'];
          }
        );
  }

  ngAfterContentInit(): void {
    setInterval(() => {
      const body: User = new User();
      body.username = this.friend;
      this.http
        .post(WEB_QQ_RECENT_MESSAGE, body)
        .subscribe(
          data => {
            this.messages = data['data'];
            console.log(JSON.stringify(this.messages));
          }
        );
    }, 1000);
  }

  doSend(value: string): void {
    if (value == null || value.trim() === '') {
      return;
    }
    // tslint:disable-next-line:prefer-const
    let message: Message = new Message();
    message.content = value;
    // tslint:disable-next-line:prefer-const
    let receviewUser = new User();
    receviewUser.username = this.friend;
    message.receiveUser = receviewUser;
    message.sendDate = new Date();
    this.http.post(WEB_QQ_PUSH, message).subscribe(
      data => {
        console.log('data:' + JSON.stringify(data));
      }
    );
    this.messages.push(message);
    this.currentMessage.content = null;
  }

}
