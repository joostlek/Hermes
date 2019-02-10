import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {Location} from '../../../../@core/data/domain/location';
import {LocationService} from '../../../../@core/data/location.service';
import {ChosenCompanyService} from '../../chosen-company.service';

@Component({
    selector: 'app-c-manage-locations',
    templateUrl: './c-manage-locations.component.html',
    styleUrls: ['./c-manage-locations.component.css'],
})
export class CManageLocationsComponent implements OnInit {
    locations: Location[];

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.getLocations();
    }

    getLocations(): void {
        this.chosenCompanyService.getCompany()
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe((company) => {
                    this.locationService.getLocationsByCompanyId(company.id)
                        .subscribe((locations) => this.locations = locations);
                },
            );

    }

}
