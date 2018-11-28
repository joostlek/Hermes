import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Location} from './domain/location';
import {LocationService} from './location.service';

@Injectable({
    providedIn: 'root',
})
export class SelectorService {
    selectedLocation: Subject<Location> = new Subject<Location>();

    constructor(
        private locationService: LocationService,
    ) {
    }

    updateLocation(data: Location) {
        this.locationService.getLocationById(data.id)
            .subscribe((location) => this.selectedLocation.next(location));
    }
}
