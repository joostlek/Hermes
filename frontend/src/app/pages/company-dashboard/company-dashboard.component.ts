import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CompanyService} from '../../@core/data/company.service';
import {Company} from '../../@core/data/domain/company';
import {ChosenCompanyService} from './chosen-company.service';

@Component({
    selector: 'app-company-dashboard',
    templateUrl: './company-dashboard.component.html',
    styleUrls: ['./company-dashboard.component.css'],
})
export class CompanyDashboardComponent implements OnInit {
    company: Company;

    constructor(
        private companyService: CompanyService,
        private chosenCompanyService: ChosenCompanyService,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.getParameter();
    }

    private getParameter(): void {
        this.route.params
            .subscribe(
                (params) => {
                    this.getCompany(+params['id']);
                },
            );
    }

    private getCompany(companyId: number): void {
        this.companyService.getCompanyById(companyId)
            .subscribe((company) => {
                    this.company = company;
                    this.chosenCompanyService.pushNewCompany(company);
                },
            );
    }

}
