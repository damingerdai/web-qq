import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MyFriendsService } from '../../service/my-friends.service';
import { User } from '../../domain/user';

@Component({
  selector: 'app-myfriends',
  templateUrl: './myfriends.component.html',
  styleUrls: ['./myfriends.component.css'],
})
export class MyfriendsComponent implements OnInit {

  users: User[];

  constructor(private myFriendsService: MyFriendsService, private http: HttpClient) { }

  ngOnInit() {
    this.http
    .get('/webqq/myFriends')
    .subscribe(
        data => {
          this.users = data['data'] as User[];
          console.log('result: ' + JSON.stringify(this.users));
          console.log('result1:' + this.users[0].username);
          console.log('result2:' + this.users[1].username);
          // return users;
        },
        error => {
          // tslint:disable-next-line:no-trailing-whitespace
        });
  }

}
