import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../@core/data/company.service';
import {Company} from '../../../@core/data/domain/company';
import {ChosenCompanyService} from '../chosen-company.service';

@Component({
    selector: 'app-edit-company-modal',
    templateUrl: './edit-company-modal.component.html',
    styleUrls: ['./edit-company-modal.component.css'],
})
export class EditCompanyModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshStream: Subject<boolean>;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    company: Company;
    open = false;

    error: string;

    basicInfoPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
    });

    locationInfoPage = new FormGroup({
        street: new FormControl('', [Validators.required]),
        houseNumber: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required]),
        city: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
    });

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private companyService: CompanyService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
                if (value === true) {
                    this.getCompany();
                }
            },
        );
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.company = company;
                    this.fillForm(company);
                },
            );
    }

    private fillForm(company: Company): void {
        this.basicInfoPage.controls['name'].setValue(company.name);
        this.locationInfoPage.controls['street'].setValue(company.street);
        this.locationInfoPage.controls['houseNumber'].setValue(company.houseNumber);
        this.locationInfoPage.controls['zipCode'].setValue(company.zipCode);
        this.locationInfoPage.controls['city'].setValue(company.city);
        this.locationInfoPage.controls['country'].setValue(company.country);
    }

    public closeModal(): void {
        this.basicInfoPage.reset();
        this.locationInfoPage.reset();
        this.openStream.next(false);
    }

    public getErrorCount(formGroup: FormGroup): number {
        let res = 0;
        for (const key in formGroup.controls) {
            if (formGroup.controls.hasOwnProperty(key)) {
                const value = formGroup.controls[key];
                if (value.errors !== null) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public updateCompany(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        if (!this.locationInfoPage.invalid && !this.basicInfoPage.invalid) {
            this.company.name = this.basicInfoPage.value['name'];
            this.company.street = this.locationInfoPage.value['street'];
            this.company.houseNumber = this.locationInfoPage.value['houseNumber'];
            this.company.zipCode = this.locationInfoPage.value['zipCode'];
            this.company.city = this.locationInfoPage.value['city'];
            this.company.country = this.locationInfoPage.value['country'];
            this.companyService.updateCompany(this.company)
                .subscribe(
                    (company: Company) => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        this.chosenCompanyService.pushNewCompany(company);
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

    public isDisabled(): boolean {
        return this.locationInfoPage.invalid || this.basicInfoPage.invalid;
    }

}
