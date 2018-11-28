import {Screen} from './screen';
import {User} from './user';

export class Image {
    id: number;
    name: string;
    url: string;
    owner: User;
    screen: Screen;
}
