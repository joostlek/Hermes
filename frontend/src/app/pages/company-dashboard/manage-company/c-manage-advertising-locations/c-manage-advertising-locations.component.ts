import {Component, OnInit} from '@angular/core';
import {ChosenCompanyService} from '../../chosen-company.service';
import {LocationService} from '../../../../@core/data/location.service';
import {Company} from '../../../../@core/data/domain/company';
import {Location} from '../../../../@core/data/domain/location';
import {Subject} from 'rxjs';

@Component({
    selector: 'app-c-manage-advertising-locations',
    templateUrl: './c-manage-advertising-locations.component.html',
    styleUrls: ['./c-manage-advertising-locations.component.css'],
})
export class CManageAdvertisingLocationsComponent implements OnInit {
    addLocationModal: Subject<boolean> = new Subject<boolean>();

    refreshList: Subject<boolean> = new Subject<boolean>();

    locations: Location[];

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.watchRefresh();
        this.refreshList.next(true);
    }

    private watchRefresh(): void {
        this.refreshList.subscribe(
            () => {
                this.getAdvertisingLocations();
            },
        );
    }

    private getAdvertisingLocations(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.locationService.getAdvertisingLocationsByCompanyId(company.id)
                        .subscribe(
                            (locations: Location[]) => {
                                this.locations = locations;
                            },
                        );
                },
            );
    }

    public openAddLocationModal(): void {
        this.addLocationModal.next(true);
    }
}
