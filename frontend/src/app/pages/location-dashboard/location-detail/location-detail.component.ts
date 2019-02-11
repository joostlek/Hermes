import {Component, OnInit} from '@angular/core';
import {Location} from '../../../@core/data/domain/location';
import {Screen} from '../../../@core/data/domain/screen';
import {ActivatedRoute, Router} from '@angular/router';
import {LocationService} from '../../../@core/data/location.service';
import {ScreenService} from '../../../@core/data/screen.service';
import {Location as NgLocation} from '@angular/common';

@Component({
    selector: 'app-location-detail',
    templateUrl: './location-detail.component.html',
    styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {
    location: Location;
    screens: Screen[] = [];
    informationActive = true;
    screensActive = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private locationService: LocationService,
        private screenService: ScreenService,
        private ngLocation: NgLocation,
    ) {
    }

    ngOnInit() {
        this.getParameter();
    }

    getParameter(): void {
        this.route.params.subscribe(
            (params) => {
                if (params.hasOwnProperty('id')) {
                    this.getLocation(+params['id']);
                } else {
                    this.getParentParameter();
                }
            },
        );
    }

    getParentParameter(): void {
        this.route.parent.parent.params.subscribe((params) => {
            this.getLocation(+params['id']);
        });
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

    goBack(): void {
        this.ngLocation.back();
    }

}
