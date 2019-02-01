import {Screen} from './screen';
import {User} from './user';

export class Location {
    id: number;
    name: string;
    screens: Screen[];
    owner: User;
    created: Date;
    updated: Date;
    street: string;
    houseNumber: string;
    city: string;
    country: string;
    zipCode: string;

    constructor(name: string) {
        this.name = name;
    }
}
