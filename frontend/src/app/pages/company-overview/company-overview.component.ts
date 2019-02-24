import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
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

    companyWizard: Subject<boolean> = new Subject<boolean>();
    allCompanies: BehaviorSubject<Company[]> = new BehaviorSubject(null);

    constructor(
        private currentUserService: CurrentUserService,
        private companyService: CompanyService,
    ) {
    }

    ngOnInit() {
        this.initializeStreams();
    }

    private initializeStreams(): void {
        this.currentUserService.getCurrentUser()
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe((value: User) => {
                    this.companyService.getCompaniesByUserId(value.id)
                        .subscribe((companies: Company[]) => {
                                this.allCompanies.next(companies);
                            },
                        );
                },
            );
    }

    public getAllCompanyStream(): Observable<Company[]> {
        return this.allCompanies.pipe(
            filter((value) => value !== null),
        );
    }

    public openCompanyWizard(): void {
        this.companyWizard.next(true);
    }

}
