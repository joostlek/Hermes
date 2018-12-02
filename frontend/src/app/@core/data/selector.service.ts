import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Location} from './domain/location';
import {LocationService} from './location.service';

@Injectable({
    providedIn: 'root',
})
export class SelectorService {

    constructor(
        private locationService: LocationService,
    ) {
        this.setLocationListener();
        this.selectedLocation.subscribe((location) => this.location = location);
        this.getLocation();
    }

    location: Location = null;
    selectedLocation: Subject<Location> = new Subject<Location>();

    private static setLocation(location: Location): void {
        sessionStorage.setItem('selected location', location.id.toString());
    }

    private setLocationListener(): void {
        this.selectedLocation.subscribe(
            (location) => {
                SelectorService.setLocation(location);
            },
        );
    }

    updateLocation(data: Location) {
        this.updateLocationById(data.id);
    }

    private updateLocationById(id: number): void {
        this.locationService.getLocationById(id)
            .subscribe((location) => this.selectedLocation.next(location));
    }

    getLocation(): void {
        const id = +sessionStorage.getItem('selected location');
        if (id !== null) {
            this.updateLocationById(id);
        }
    }
}
