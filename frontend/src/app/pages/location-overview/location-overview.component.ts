import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {User} from '../../@core/data/domain/user';
import {BehaviorSubject, Observable} from 'rxjs';
import {LocationService} from '../../@core/data/location.service';

@Component({
    selector: 'app-location-overview',
    templateUrl: './location-overview.component.html',
    styleUrls: ['./location-overview.component.css'],
})
export class LocationOverviewComponent implements OnInit {
    user: User = null;
    companies: Company[];

    allCompanyStream: BehaviorSubject<Company[]> = new BehaviorSubject(null);
    personalCompanyStream: BehaviorSubject<Company[]> = new BehaviorSubject(null);
    advertisingCompanyStream: BehaviorSubject<Company[]> = new BehaviorSubject(null);

    constructor(
        private companyService: CompanyService,
        private currentUserService: CurrentUserService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.initializeStreams();
    }

    private getCurrentUser(): Observable<User> {
        return this.currentUserService.getCurrentUser()
            .pipe(
                filter((value) => value !== null),
            );
    }

    private initializeStreams(): void {
        this.getCurrentUser()
            .subscribe(
                (user) => {
                    this.companyService.getCompaniesByUserId(user.id)
                        .subscribe(
                            (companies) => {
                                this.allCompanyStream.next(companies);
                            },
                        );
                    this.companyService.getPersonalCompanies(user.id)
                        .subscribe(
                            (companies) => {
                                this.personalCompanyStream.next(companies);
                            },
                        );
                    this.companyService.getAdvertisingCompanies(user.id)
                        .subscribe(
                            (companies) => {
                                this.advertisingCompanyStream.next(companies);
                            },
                        );
                },
            );
    }

}
