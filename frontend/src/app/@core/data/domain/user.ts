import {Image} from './image';
import {Location} from './location';

export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    created: Date;
    updated: Date;
    images: Image[];
    locations: Location[];


}
