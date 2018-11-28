import {Component, OnInit} from '@angular/core';
import {SelectorService} from '../@core/data/selector.service';
import {LocationService} from '../@core/data/location.service';
import {Location} from '../@core/data/domain/location';

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

    constructor(private selectorService: SelectorService,
                private locationService: LocationService) {
    }

    ngOnInit() {
        this.getLocations();
        this.getSelectedLocation();
    }

    getLocations() {
        this.locationService.getAllLocations()
            .subscribe((locations) => this.locations = locations);
    }

    updateLocation(location: Location) {
        this.selectorService.updateLocation(location);
    }

    getSelectedLocation() {
        this.selectorService.selectedLocation
            .subscribe((location) => this.selectedLocation = location);
    }

    isLocationSelected(): boolean {
        return this.selectedLocation != null;
    }

}
