import {Component, OnInit} from '@angular/core';
import {Location} from '../../../@core/data/domain/location';
import {ChosenLocationService} from '../chosen-location.service';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-location-detail',
    templateUrl: './location-detail.component.html',
    styleUrls: ['./location-detail.component.css'],
})
export class LocationDetailComponent implements OnInit {
    location: Location;
    company: Company;

    constructor(
        private chosenLocationService: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.getLocation();
        this.getCompany();
    }

    private getLocation(): void {
        this.chosenLocationService.getLocation()
            .subscribe((location) => {
                    this.location = location;
                },
            );
    }

    private getCompany(): void {
        this.chosenLocationService.getCompany()
            .subscribe((company) => {
                    this.company = company;
                },
            );
    }
}
