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

    company: Company;

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
        this.getCompany();
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.company = company;
                },
            );
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
        if (!this.locationForm.invalid) {
            this.companyService.addAdvertisingLocationToCompany(+this.locationForm.controls['locationId'].value, this.company.id)
                .subscribe(
                    (newCompany: Company) => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        this.chosenCompanyService.pushNewCompany(newCompany);
                        this.refreshStream.next(true);
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

}
