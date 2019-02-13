import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-company-list',
    templateUrl: './company-list.component.html',
    styleUrls: ['./company-list.component.css'],
})
export class CompanyListComponent implements OnInit {
    @Input() companyStream: Observable<Company[]>;
    companies: Company[];

    constructor() {
    }

    ngOnInit() {
        this.getCompanies();
    }

    private getCompanies(): void {
        this.companyStream.subscribe(
            (companies: Company[]) => {
                this.companies = companies;
            },
        );
    }

}
