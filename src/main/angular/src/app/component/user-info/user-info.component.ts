import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../domain/user';

const WEB_QQ_MY_INFO = '/webqq/myInfo';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user: User;

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.http
      .post(WEB_QQ_MY_INFO, null)
      .subscribe(
        data => {
          this.user = data['data'];
        }
      );
  }
}
