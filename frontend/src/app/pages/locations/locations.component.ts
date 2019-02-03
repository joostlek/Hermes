import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';
import {SelectorService} from '../../@core/data/selector.service';

@Component({
    selector: 'app-locations',
    templateUrl: './locations.component.html',
    styleUrls: ['./locations.component.css'],
})
export class LocationsComponent implements OnInit {
    wizardOpen = false;
    locations: Location[] = [];
    dataGridError: any;
    dataGridErrorFlag = false;

    constructor(
        private locationService: LocationService,
        private selectorService: SelectorService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.getLocations();
    }

    getLocations(): void {
        this.locationService.getAllLocations()
            .subscribe((locations) => this.locations = locations,
                (err) => {
                    this.dataGridError = err;
                    this.dataGridErrorFlag = true;
                    throw err;
                });
    }

    navigate(location: Location): void {
        this.router.navigate(['/locations/' + location.id]);
    }

    updateLocation(location: Location) {
        this.selectorService.updateSelectedLocation(location);
    }

}
