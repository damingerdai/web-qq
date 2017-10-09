import { User } from './user';

export class FriendsResponse {
    success: boolean;
    data: User[];
    error: Error;
}
