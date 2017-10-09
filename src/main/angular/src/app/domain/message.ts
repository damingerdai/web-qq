import { User } from './user';

export class Message {
    id: string;
    sendUser: User;
    receiveUser: User;
    content: string;
    sendDate: Date;
}
