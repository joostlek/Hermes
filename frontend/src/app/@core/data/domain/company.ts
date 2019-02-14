import {Image} from './image';
import {Location} from './location';
import {User} from './user';

export class Company {
    id: number;
    name: string;
    street: string;
    houseNumber: string;
    phoneNumber: string;
    city: string;
    country: string;
    zipCode: string;
    advertisingLocations: Location[];
    locations: Location[];
    users: User[];
    images: Image[];
    created: Date;
    updated: Date;
}
