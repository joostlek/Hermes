import {Image} from './image';
import {Location} from './location';

export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    images: Image[];
    locations: Location[];
}
