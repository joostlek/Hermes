import {Component, Input, OnInit} from '@angular/core';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {Company} from '../../../../../@core/data/domain/company';
import {Location} from '../../../../../@core/data/domain/location';
import {LocationService} from '../../../../../@core/data/location.service';
import {ChosenLocationService} from '../../../chosen-location.service';

@Component({
    selector: 'app-remove-advertising-company-modal',
    templateUrl: './remove-advertising-company-modal.component.html',
    styleUrls: ['./remove-advertising-company-modal.component.css'],
})
export class RemoveAdvertisingCompanyModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshList: Subject<boolean>;
    @Input() company: Company;

    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    location: Location;

    open = false;
    error: string;

    constructor(
        private chosenLocationService: ChosenLocationService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
        this.getLocation();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value: boolean) => {
                this.open = value;
            },
        );
    }

    private getLocation(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location: Location) => {
                    this.location = location;
                },
            );
    }

    public closeModal(): void {
        this.company = undefined;
        this.error = undefined;
        this.openStream.next(false);
    }

    public removeCompany(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.locationService.removeAdvertisingCompanyFromLocation(this.location.id, this.company.id)
            .subscribe(
                (location: Location) => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
                    this.chosenLocationService.pushNewLocation(location);
                    this.refreshList.next(true);
                    this.closeModal();
                },
                (error) => {
                    this.error = JSON.parse(error.error)['message'];
                    this.submitButtonState = ClrLoadingState.ERROR;
                    console.error(this.error);
                },
            );
    }

}
