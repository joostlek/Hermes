import {Image} from './image';
import {Location} from './location';

export class Screen {
    id: number;
    name: string;
    width: number;
    height: number;
    location: Location;
    images: Image[];
}
