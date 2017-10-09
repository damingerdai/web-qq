import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MyfriendsComponent } from './component/myfriends/myfriends.component';
import { AddFriendComponent } from './component/add-friend/add-friend.component';
import { UserInfoComponent } from './component/user-info/user-info.component';
import { ChatRoomComponent } from './component/chat-room/chat-room.component';

const appRoutes: Routes = [
  { path: 'myFriends', component: MyfriendsComponent },
  { path: 'addFriend', component: AddFriendComponent },
  { path: 'userInfo', component: UserInfoComponent },
  { path: 'chatRoom/:username', component: ChatRoomComponent },
  { path: '',   redirectTo: '/myFriends', pathMatch: 'full' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true, useHash: true }// debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {}
