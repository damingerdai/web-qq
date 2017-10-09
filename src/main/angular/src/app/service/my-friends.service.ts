import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../domain/user';
import { FriendsResponse } from '../domain/response';

@Injectable()
export class MyFriendsService {

    constructor(private http: HttpClient) { }

    getFriends(): User[] {
        let results: User[];
        this.http
            .get<FriendsResponse>('/webqq/myFriends')
            .subscribe(
                data => {
                    results = data.data as User[];
                    return results;
                });
        console.log('result:' + results);
        return results;
    }
}

