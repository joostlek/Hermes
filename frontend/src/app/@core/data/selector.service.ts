import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Location} from './domain/location';

@Injectable({
    providedIn: 'root',
})
export class SelectorService {
    private location$ = new BehaviorSubject(null);

    constructor() {
        this.getSelectedLocation();
        this.addSessionStorageListener();
    }

    updateSelectedLocation(location: Location): void {
        this.location$.next(location);
    }

    getSelectedLocation(): BehaviorSubject<Location> {
        if (this.location$.getValue() === null && sessionStorage.getItem('selectedLocation')) {
            this.location$.next(JSON.parse(sessionStorage.getItem('selectedLocation')));
        }
        return this.location$;
    }

    removeSelectedLocation(): void {
        this.location$.next(null);
    }

    private addSessionStorageListener() {
        this.location$.subscribe((location: Location) => {
            if (location !== null) {
                sessionStorage.setItem('selectedLocation', JSON.stringify(location));
            } else {
                sessionStorage.removeItem('selectedLocation');
            }
        });
    }
}
