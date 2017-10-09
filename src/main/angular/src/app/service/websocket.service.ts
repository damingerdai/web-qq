import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { Observer } from 'rxjs/Observer';

import { Message } from '../domain/message';



export type onMessageCallback = (buf: ArrayBuffer | string) => void;
export type onOpenCallback = () => void;
export type onCloseCallback = () => void;
export type onErrorCallback = () => void;

@Injectable()
export class WebsocketService {
    ws: WebSocket;

    constructor() {
       // tslint:disable-next-line:no-trailing-whitespace
       
    }
}
