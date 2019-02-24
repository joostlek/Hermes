import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ClrWizard, ClrWizardPage} from '@clr/angular';
import {CompanyService} from '../../../@core/data/company.service';
import {Company} from '../../../@core/data/domain/company';
import {Subject} from 'rxjs';

@Component({
    selector: 'app-company-wizard',
    templateUrl: './company-wizard.component.html',
    styleUrls: ['./company-wizard.component.css'],
})
export class CompanyWizardComponent implements OnInit {
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;
    @Input() openStream: Subject<boolean>;

    loadingFlag = false;
    errorFlag = false;
    error: any;
    open = false;

    basicInfoPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
        phoneNumber: new FormControl('', [Validators.required]),
    });

    locationInfoPage = new FormGroup({
        street: new FormControl('', [Validators.required]),
        houseNumber: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required]),
        city: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
    });

    constructor(
        private companyService: CompanyService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value: boolean) => {
                this.open = value;
            },
        );
    }

    public createCompany(): void {
        this.errorFlag = false;
        this.loadingFlag = true;
        if (!this.basicInfoPage.invalid && !this.locationInfoPage.invalid) {
            const company = new Company();
            company.name = this.basicInfoPage.value['name'];
            company.phoneNumber = this.basicInfoPage.value['phoneNumber'];
            company.street = this.locationInfoPage.value['street'];
            company.houseNumber = this.locationInfoPage.value['houseNumber'];
            company.zipCode = this.locationInfoPage.value['zipCode'];
            company.city = this.locationInfoPage.value['city'];
            company.country = this.locationInfoPage.value['country'];
            this.companyService.addCompany(company)
                .subscribe((comp) => {
                        this.doFinish();
                        this.navigate(comp);
                    },
                    (err) => {
                        this.loadingFlag = false;
                        this.errorFlag = true;
                        this.error = err;
                        throw err;
                    },
                );
        }
    }

    private navigate(company: Company): void {
        this.router.navigate([company.id]);
    }

    public reset(): void {
        this.wizard.reset();
        this.basicInfoPage.reset();
        this.wizard.close();
        this.loadingFlag = false;
        this.errorFlag = false;
    }

    public doCancel(): void {
        this.reset();
    }

    public goBack(): void {
        this.wizard.previous();
    }

    public doFinish(): void {
        this.wizard.forceFinish();
        this.reset();
    }

}
