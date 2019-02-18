import {Location} from '@angular/common';
import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../@core/data/company.service';
import {Company} from '../../../@core/data/domain/company';
import {ChosenLocationService} from '../chosen-location.service';

@Component({
    selector: 'app-choose-company-modal',
    templateUrl: './choose-company-modal.component.html',
    styleUrls: ['./choose-company-modal.component.css'],
})
export class ChooseCompanyModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() companyStream: Subject<Company[]>;

    open = false;
    companies: Company[];

    companyForm = new FormGroup({
        companyId: new FormControl('', [Validators.required]),
    });

    constructor(
        private chosenLocationService: ChosenLocationService,
        private companyService: CompanyService,
        private location: Location,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
        this.getCompanies();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value: boolean) => {
                this.open = value;
            },
        );
    }

    private getCompanies(): void {
        this.companyStream.subscribe(
            (companies: Company[]) => {
                this.companies = companies;
                this.companyForm.controls['companyId'].setValue(companies[0].id);
            },
        );
    }

    private getCompany(id: number): void {
        this.companyService.getCompanyById(id)
            .subscribe((company) => {
                    this.chosenLocationService.pushNewCompany(company);
                },
            );
    }

    public chooseCompany(): void {
        if (!this.companyForm.invalid) {
            const id = +this.companyForm.value['companyId'];
            this.getCompany(id);
            this.closeModal();
        }
    }

    private closeModal(): void {
        this.openStream.next(false);
    }

    public goBack(): void {
        this.location.back();
    }
}
