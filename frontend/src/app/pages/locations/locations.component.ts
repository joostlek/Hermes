import {Component, OnInit} from '@angular/core';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';

@Component({
    selector: 'app-locations',
    templateUrl: './locations.component.html',
    styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {
    locations: Location[] = [];

    constructor(
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.getLocations();
    }

    getLocations(): void {
        this.locationService.getAllLocations()
            .subscribe((locations) => this.locations = locations);
    }

}
