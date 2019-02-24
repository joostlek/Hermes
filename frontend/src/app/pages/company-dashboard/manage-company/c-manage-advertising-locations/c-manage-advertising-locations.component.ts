import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {Company} from '../../../../@core/data/domain/company';
import {Location} from '../../../../@core/data/domain/location';
import {ChosenCompanyService} from '../../chosen-company.service';

@Component({
    selector: 'app-c-manage-advertising-locations',
    templateUrl: './c-manage-advertising-locations.component.html',
    styleUrls: ['./c-manage-advertising-locations.component.css'],
})
export class CManageAdvertisingLocationsComponent implements OnInit {
    addLocationModal: Subject<boolean> = new Subject<boolean>();
    removeLocationModal: Subject<boolean> = new Subject<boolean>();
    refreshList: Subject<boolean> = new Subject<boolean>();

    locations: Location[];

    locationToBeDeleted: Location;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
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
                    this.locations = company.advertisingLocations;
                },
            );
    }

    public openAddLocationModal(): void {
        this.addLocationModal.next(true);
    }

    public openRemoveLocationModal(location: Location): void {
        this.locationToBeDeleted = location;
        this.removeLocationModal.next(true);
    }
}
