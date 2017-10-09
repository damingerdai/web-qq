/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { MyFriendsService } from './my-friends.service';

describe('Service: MyFriends', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyFriendsService]
    });
  });

  it('should ...', inject([MyFriendsService], (service: MyFriendsService) => {
    expect(service).toBeTruthy();
  }));
});