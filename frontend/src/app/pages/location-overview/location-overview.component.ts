import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {User} from '../../@core/data/domain/user';

@Component({
    selector: 'app-location-overview',
    templateUrl: './location-overview.component.html',
    styleUrls: ['./location-overview.component.css'],
})
export class LocationOverviewComponent implements OnInit {
    user: User = null;
    companies: Company[];

    constructor(
        private companyService: CompanyService,
        private currentUserService: CurrentUserService,
    ) {
    }

    ngOnInit() {
        this.getCurrentUser()
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe(
                (value) => {
                    this.user = value;
                    this.getCompanies();
                },
            );
    }

    getCurrentUser() {
        return this.currentUserService.getCurrentUser();
    }

    getCompanies(): void {
        this.companyService.getCompaniesByUserId(this.user.id)
            .subscribe((value) => {
                this.companies = value;
            });
    }

}
