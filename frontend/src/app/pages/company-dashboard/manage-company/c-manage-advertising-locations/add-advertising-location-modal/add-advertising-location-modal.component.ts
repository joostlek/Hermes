import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../../../@core/data/company.service';
import {Company} from '../../../../../@core/data/domain/company';
import {Location} from '../../../../../@core/data/domain/location';
import {LocationService} from '../../../../../@core/data/location.service';
import {ChosenCompanyService} from '../../../chosen-company.service';
import {ClrLoadingState} from '@clr/angular';

@Component({
    selector: 'app-add-advertising-location-modal',
    templateUrl: './add-advertising-location-modal.component.html',
    styleUrls: ['./add-advertising-location-modal.component.css'],
})
export class AddAdvertisingLocationModalComponent implements OnInit {
    @Input() refreshStream: Subject<boolean>;
    @Input() openStream: Subject<boolean>;

    open = false;
    error: string;

    companies: Company[];
    locations: Location[];

    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    constructor(
        private companyService: CompanyService,
        private locationService: LocationService,
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    locationForm = new FormGroup({
        companyId: new FormControl('', [Validators.required]),
        locationId: new FormControl('', [Validators.required]),
    });

    ngOnInit() {
        this.watchOpen();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (open: boolean) => {
                this.open = open;
                if (open === true && this.companies === undefined) {
                    this.getCompanies();
                }
            },
        );
    }

    private getCompanies(): void {
        this.companyService.getAllCompanies()
            .subscribe(
                (companies: Company[]) => {
                    this.companies = companies;
                },
            );
    }

    public updateLocations(companyId: number) {
        this.locationService.getLocationsByCompanyId(companyId)
            .subscribe(
                (locations: Location[]) => {
                    this.locations = locations;
                },
            );
    }

    public closeModal(): void {
        this.openStream.next(false);
    }

    public addLocation(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.locationService.addAdvertisingLocationToCompany(+this.locationForm.controls['locationId'].value, company.id)
                        .subscribe(
                            () => {
                                this.submitButtonState = ClrLoadingState.SUCCESS;
                                this.refreshStream.next(true);
                                this.closeModal();
                            },
                            (error) => {
                                this.error = JSON.parse(error.error)['message'];
                                this.submitButtonState = ClrLoadingState.ERROR;
                                console.error(this.error);
                            },
                        );
                },
            );
    }

}
