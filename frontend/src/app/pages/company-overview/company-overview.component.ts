import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {User} from '../../@core/data/domain/user';

@Component({
    selector: 'app-company-overview',
    templateUrl: './company-overview.component.html',
    styleUrls: ['./company-overview.component.css'],
})
export class CompanyOverviewComponent implements OnInit {
    companies: Company[];
    wizardOpen = false;

    constructor(
        private currentUserService: CurrentUserService,
        private companyService: CompanyService,
    ) {
    }

    ngOnInit() {
        this.getCompanies();
    }

    getCompanies(): void {
        this.currentUserService.getCurrentUser()
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe((value: User) => {
                this.companyService.getCompaniesByUserId(value.id)
                    .subscribe((value1) => this.companies = value1);
            });

    }

}
