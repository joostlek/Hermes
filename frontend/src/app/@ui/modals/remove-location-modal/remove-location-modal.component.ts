import {Component, Input, OnInit} from '@angular/core';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../@core/data/company.service';
import {Company} from '../../../@core/data/domain/company';
import {Location} from '../../../@core/data/domain/location';
import {LocationService} from '../../../@core/data/location.service';
import {ChosenCompanyService} from '../../../pages/company-dashboard/chosen-company.service';

@Component({
    selector: 'app-remove-location-modal',
    templateUrl: './remove-location-modal.component.html',
    styleUrls: ['./remove-location-modal.component.css'],
})
export class RemoveLocationModalComponent implements OnInit {
    @Input('open') openStream: Subject<boolean>;
    @Input('refreshLocation') refreshList: Subject<boolean>;
    @Input('location') location: Location;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;
    error: string;
    company: Company;

    constructor(
        private companyService: CompanyService,
        private locationService: LocationService,
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.checkOpenStream();
        this.getCompany();
    }

    private checkOpenStream(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
            },
        );
    }

    public closeModal(): void {
        this.error = undefined;
        this.openStream.next(false);
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company) => {
                    this.company = company;
                },
            );
    }

    public removeLocation(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.locationService.deleteLocation(this.location.id)
            .subscribe(
                () => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
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
