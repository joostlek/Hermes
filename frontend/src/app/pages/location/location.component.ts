import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';
import {Screen} from '../../@core/data/domain/screen';
import {ScreenService} from '../../@core/data/screen.service';

@Component({
    selector: 'app-location',
    templateUrl: './location.component.html',
    styleUrls: ['./location.component.css'],
})
export class LocationComponent implements OnInit {
    location: Location;
    screens: Screen[] = [];

    constructor(
        private route: ActivatedRoute,
        private locationService: LocationService,
        private screenService: ScreenService,
    ) {
    }

    ngOnInit() {
        this.getParameter();
    }

    getParameter(): void {
        this.route.params.subscribe(
            (params) => {
                this.getLocation(+params['id']);
            },
        );
    }

    getLocation(id: number): void {
        this.locationService.getLocationById(id)
            .subscribe((location) => {
                this.location = location;
                this.getScreens();
            });
    }

    getScreens(): void {
        this.screenService.getScreensByLocation(this.location)
            .subscribe((screens) => this.screens = screens);
    }

}
