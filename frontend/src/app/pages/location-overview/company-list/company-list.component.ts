import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {filter} from 'rxjs/operators';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-company-list',
    templateUrl: './company-list.component.html',
    styleUrls: ['./company-list.component.css'],
})
export class CompanyListComponent implements OnInit {
    @Input('companyStream') companyStream: BehaviorSubject<Company[]>;
    companies: Company[];

    constructor() {
    }

    ngOnInit() {
        this.getCompanies();
    }

    private getCompanies(): void {
        this.companyStream
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe(
                (companies) => {
                    this.companies = companies;
                },
            );
    }
}
