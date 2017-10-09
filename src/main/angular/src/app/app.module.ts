import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MyfriendsComponent } from './component/myfriends/myfriends.component';
import { AddFriendComponent } from './component/add-friend/add-friend.component';
import { UserInfoComponent } from './component/user-info/user-info.component';
import { ChatRoomComponent } from './component/chat-room/chat-room.component';

import { MyFriendsService } from './service/my-friends.service';
import { WebsocketService } from './service/websocket.service';

@NgModule({
  declarations: [
    AppComponent,
    MyfriendsComponent,
    AddFriendComponent,
    UserInfoComponent,
    ChatRoomComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    MyFriendsService,
    WebsocketService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
