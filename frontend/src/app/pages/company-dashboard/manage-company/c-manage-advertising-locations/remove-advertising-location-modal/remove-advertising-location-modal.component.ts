import {Component, Input, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {Company} from '../../../../../@core/data/domain/company';
import {Location} from '../../../../../@core/data/domain/location';
import {ChosenCompanyService} from '../../../chosen-company.service';
import {LocationService} from '../../../../../@core/data/location.service';
import {ClrLoadingState} from '@clr/angular';

@Component({
    selector: 'app-remove-advertising-location-modal',
    templateUrl: './remove-advertising-location-modal.component.html',
    styleUrls: ['./remove-advertising-location-modal.component.css'],
})
export class RemoveAdvertisingLocationModalComponent implements OnInit {
    @Input() location: Location;
    @Input() openStream: Subject<boolean>;
    @Input() refreshList: Subject<boolean>;

    open = false;
    error: string;
    company: Company;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
        this.getCompany();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (open: boolean) => {
                this.open = open;
            },
        );
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.company = company;
                },
            );
    }

    public closeModal(): void {
        this.error = undefined;
        this.company = undefined;
        this.openStream.next(false);
    }

    public removeLocation(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.locationService.removeAdvertisingCompanyFromLocation(this.location.id, this.company.id)
            .subscribe(
                (location: Location) => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
                    this.closeModal();
                },
                (error) => {
                    this.error = JSON.parse(error)['message'];
                    this.submitButtonState = ClrLoadingState.ERROR;
                    console.error(error);
                },
            );
    }

}
