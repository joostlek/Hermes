import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {Location} from '../../@core/data/domain/location';
import {User} from '../../@core/data/domain/user';
import {LocationService} from '../../@core/data/location.service';
import {ChosenLocationService} from './chosen-location.service';
import {BehaviorSubject, Subject} from 'rxjs';

@Component({
    selector: 'app-location-dashboard',
    templateUrl: './location-dashboard.component.html',
    styleUrls: ['./location-dashboard.component.css'],
    providers: [ChosenLocationService],
})
export class LocationDashboardComponent implements OnInit {
    location: Location;
    company: Company;
    user: User;

    chooseCompanyModal: Subject<boolean> = new Subject<boolean>();
    companyChoices: Subject<Company[]> = new Subject<Company[]>();

    admin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    constructor(
        private route: ActivatedRoute,
        private locationService: LocationService,
        private companyService: CompanyService,
        private currentUserService: CurrentUserService,
        private chosenLocation: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.getCurrentUser();
        console.log(this.chosenLocation);
    }

    private getCurrentUser(): void {
        this.currentUserService.getCurrentUser()
            .subscribe((value) => {
                    this.user = value;
                    this.getParameter();
                },
            );
    }

    private getParameter(): void {
        this.route.params
            .subscribe(
                (params) => {
                    this.getLocation(+params['id']);
                },
            );
    }

    private getLocation(id: number): void {
        console.log('kek');
        this.locationService.getLocationById(id)
            .subscribe((location) => {
                    this.location = location;
                this.watchCompany();
                this.chosenLocation.pushNewLocation(location);
                this.getActingCompany();
                },
            );
    }

    private getActingCompany(): void {
        const tempCompany = sessionStorage.getItem('selectedCompany');
        console.log(tempCompany);
        if (tempCompany !== null) {
            this.getCompany(JSON.parse(tempCompany)[this.location.id]['id']);
        } else {
            const companies = [];
            for (const key in this.user.companies) {
                if (this.user.companies.hasOwnProperty(key)) {
                    const company = this.user.companies[key];
                    for (const adKey in this.location.advertisingCompanies) {
                        if (this.location.advertisingCompanies.hasOwnProperty(adKey)) {
                            const adCompany = this.location.advertisingCompanies[adKey];
                            if (adCompany['id'] === company['id']) {
                                companies.push(company);
                            }
                        }
                    }
                    if (company['id'] === this.location.company.id) {
                        companies.push(company);
                    }
                }
            }
            if (companies.length === 1) {
                this.getCompany(companies[0]['id']);
            } else if (companies.length > 1) {
                this.openCompanyChooser();
                this.companyChoices.next(companies);
            }
        }
    }

    private openCompanyChooser(): void {
        this.chooseCompanyModal.next(true);
    }

    private putSessionStorage(companyId: number): void {
        const tempCompany = sessionStorage.getItem('selectedCompany');
        let res = {};
        if (tempCompany !== null) {
            res = JSON.parse(tempCompany);
        }
        res[this.location.id] = {};
        res[this.location.id]['id'] = companyId;
        sessionStorage.setItem('selectedCompany', JSON.stringify(res));
    }

    private getCompany(id: number): void {
        this.companyService.getCompanyById(id)
            .subscribe((company) => {
                this.chosenLocation.pushNewCompany(company);
                },
            );
    }

    private watchCompany(): void {
        this.chosenLocation.getCompany()
            .subscribe(
                (company: Company) => {
                    console.log(company.id);
                    this.putSessionStorage(company.id);
                    this.company = company;
                    this.checkRights();
                },
            );
    }

    private checkRights(): void {
        let res = false;
        this.company.locations.forEach(
            (location: Location) => {
                if (location.id === this.location.id) {
                    res = true;
                }
            },
        );
        this.admin.next(res);
    }

}
