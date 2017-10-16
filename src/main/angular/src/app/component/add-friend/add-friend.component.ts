import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../../domain/user';

@Component({
  selector: 'app-add-friend',
  templateUrl: './add-friend.component.html',
  styleUrls: ['./add-friend.component.css']
})

export class AddFriendComponent implements OnInit {

  friends: User[];

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    // this.users = USERS;
  }

  onKey(value: string): void {
    if (value == null || value === '') {
      return;
    }
    const body  = { 'condition': value };
    // tslint:disable-next-line:max-line-length
    this.http.post('/webqq/findMoreUser', value).
      subscribe(data => {
        this.friends = data['data'];
        console.log('result:' + JSON.stringify(this.friends));
      });
  }

  addFriend(user: User): void {
    const body: User = new User();
    body.id = user.id;
    body.username = user.username;
    body.email = user.email;
    this.http.post('/webqq/addRelationship', body).
      subscribe(data => {
        this.router.navigate(['/myFriends']);
      }, error => {

      });
  }

}
