import { HttpClient } from '@angular/common/http';
import { Component, OnInit, AfterContentInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location, DatePipe  } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { Message } from '../../domain/message';
import { User } from './../../domain/user';
import { WebsocketService } from '../../service/websocket.service';

const WEB_QQ_PUSH = '/webqq/chat';
const WEB_QQ_RECENT_MESSAGE = '/webqq/recent/message';
const WEB_QQ_NEWEST_MESSAGE = '/webqq/newest/message';


@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css'],
  providers: [
    DatePipe
  ]
})
export class ChatRoomComponent implements OnInit, AfterContentInit, OnDestroy {

  friend: string;
  messages: Message[] = new Array<Message>();
  currentMessage: Message = new Message();
  timer: any;

  constructor(
    private route: ActivatedRoute,
    private Location: Location,
    private http: HttpClient,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.friend = params.get('username'))
      .subscribe();
      const api: string =  WEB_QQ_RECENT_MESSAGE + '/' + this.friend;
      this.http
      .get(api)
      .subscribe(
        data => {
          this.messages = data['data'];
          console.log(JSON.stringify(data));
        }
      );
  }

  ngAfterContentInit(): void {
    /*
    this.timer = setInterval(() => {
      if (this.messages.length > 0) {
        const lastMessage: Message = this.messages[this.messages.length - 1];
        console.log('lastMessage:' + JSON.stringify(lastMessage));
        // tslint:disable-next-line:max-line-length
        const api = WEB_QQ_NEWEST_MESSAGE + '/' + this.friend + '?beginDateTime=' + this.format(this.messages[this.messages.length - 1].sendDate) + '&endDateTime=' + this.format(new Date());
        this.http
        .get(api)
        .subscribe(
          data => {
            if (data['data'] != null) {
              this.messages.push(data['data']);
            }
            console.log(JSON.stringify(data));
          }
        );
      }
    }, 1000);
    */
    this.timer = setInterval( () => {
      const api: string =  WEB_QQ_RECENT_MESSAGE + '/' + this.friend;
      this.http
      .get(api)
      .subscribe(
        data => {
          this.messages = data['data'];
          console.log(JSON.stringify(data));
        });
    } );
  }

  ngOnDestroy(): void {
    if (this.timer != null ) {
      clearInterval(this.timer);
    }
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

  format(date: Date): string {
      return this.datePipe.transform(date, 'yyyy-mm-dd hh:mm:ss');
  }

}
