import {Component, OnInit} from '@angular/core';
import {Location} from '../@core/data/domain/location';
import {LocationService} from '../@core/data/location.service';
import {SelectorService} from '../@core/data/selector.service';
import {CurrentUserService} from '../@core/data/current-user.service';
import {User} from '../@core/data/domain/user';

@Component({
    selector: 'app-pages',
    templateUrl: './pages.component.html',
    styleUrls: ['./pages.component.css'],
    host: {
        '[class.content-container]': 'true',
    },
})
export class PagesComponent implements OnInit {
    locations: Location[] = [];
    selectedLocation: Location;
    user: User;

    constructor(private selectorService: SelectorService,
                private locationService: LocationService,
                private currentUserService: CurrentUserService) {
    }

    ngOnInit() {
        this.getLocations();
        this.getSelectedLocation();
        this.getCurrentUser();
    }

    getCurrentUser() {
        this.currentUserService.getCurrentUser()
            .subscribe((user: User) => {
                this.user = user;
            });
    }

    getLocations() {
        this.locationService.getPersonalLocationsByUserId(1)
            .subscribe((locations) => this.locations = locations);
    }

    updateLocation(location: Location) {
        this.selectorService.updateSelectedLocation(location);
    }

    getSelectedLocation() {
        this.selectorService.getSelectedLocation()
            .subscribe((location) => this.selectedLocation = location);
    }

    isLocationSelected(): boolean {
        return this.selectedLocation != null;
    }

    isGivenLocationIdSelected(id: number): boolean {
        if (this.selectedLocation === null || this.selectedLocation === undefined) {
            return false;
        }
        return this.selectedLocation.id === id;
    }

}
