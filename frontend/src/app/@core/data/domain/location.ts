import {User} from './user';
import {Screen} from './screen';

export class Location {
    id: number;
    name: string;
    screens: Screen[];
    owner: User;

    constructor(name: string) {
        this.name = name;
    }
}
