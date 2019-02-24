import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {Location} from '../../../../@core/data/domain/location';
import {LocationService} from '../../../../@core/data/location.service';
import {ChosenCompanyService} from '../../chosen-company.service';

@Component({
    selector: 'app-c-manage-locations',
    templateUrl: './c-manage-locations.component.html',
    styleUrls: ['./c-manage-locations.component.css'],
})
export class CManageLocationsComponent implements OnInit {
    private locationLoadStream$: Subject<boolean> = new Subject();

    createLocationWizard: Subject<boolean> = new Subject();
    deleteLocationModal: Subject<boolean> = new Subject();
    refreshLocationList: Subject<boolean> = new Subject();

    locations: Location[];
    locationToBeDeleted: Location;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.checkRefresh();
        this.refreshLocationList.next(true);
    }

    private getLocations(): void {
        this.chosenCompanyService.getCompany()
            .pipe(
                takeUntil(this.locationLoadStream$),
            )
            .subscribe((company) => {
                    this.locationService.getLocationsByCompanyId(company.id)
                        .pipe(
                            takeUntil(this.locationLoadStream$),
                        )
                        .subscribe((locations) => {
                                this.locations = locations;
                            },
                        );
                },
            );
    }

    private checkRefresh(): void {
        this.locationToBeDeleted = undefined;
        this.refreshLocationList
            .subscribe(
                () => {
                    this.locationLoadStream$.next(true);
                    this.getLocations();
                },
            );
    }

    public openLocationWizard(): void {
        this.createLocationWizard.next(true);
    }

    public openDeleteLocationModal(location: Location): void {
        this.locationToBeDeleted = location;
        this.deleteLocationModal.next(true);
    }

}
