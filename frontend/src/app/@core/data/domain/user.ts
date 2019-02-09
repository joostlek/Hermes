import {Company} from './company';
import {Image} from './image';

export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    created: Date;
    updated: Date;
    images: Image[];
    companies: Company[];

}
