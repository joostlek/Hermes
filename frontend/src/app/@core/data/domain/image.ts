import {Company} from './company';
import {Screen} from './screen';
import {User} from './user';

export class Image {
    id: number;
    name: string;
    url: string;
    uploader: User;
    company: Company;
    screen: Screen;
}
