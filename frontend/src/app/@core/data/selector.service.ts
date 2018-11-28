import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class SelectorService {
    selectedLocation: Subject<Location> = new Subject<Location>();

    constructor() {
    }

    updateLocation(data: Location) {
        this.selectedLocation.next(data);
    }
}
