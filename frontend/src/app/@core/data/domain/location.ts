import {Company} from './company';
import {Screen} from './screen';

export class Location {
    id: number;
    name: string;
    screens: Screen[];
    company: Company;
    created: Date;
    updated: Date;
    street: string;
    houseNumber: string;
    city: string;
    country: string;
    zipCode: string;
}
