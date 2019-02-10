import {Component, OnInit} from '@angular/core';
import {Company} from '../../../@core/data/domain/company';
import {ChosenCompanyService} from '../chosen-company.service';

@Component({
    selector: 'app-company-detail',
    templateUrl: './company-detail.component.html',
    styleUrls: ['./company-detail.component.css'],
})
export class CompanyDetailComponent implements OnInit {
    company: Company;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.getCompany();
    }

    getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe((company) => {
                    this.company = company;
                },
            );
    }
}
