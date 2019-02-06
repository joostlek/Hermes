import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {Location} from '../../@core/data/domain/location';
import {User} from '../../@core/data/domain/user';
import {LocationService} from '../../@core/data/location.service';
import {ChosenLocationService} from './chosen-location.service';

@Component({
    selector: 'app-location-dashboard',
    templateUrl: './location-dashboard.component.html',
    styleUrls: ['./location-dashboard.component.css'],
})
export class LocationDashboardComponent implements OnInit {
    location: Location;
    company: Company;
    user: User;

    constructor(
        private route: ActivatedRoute,
        private locationService: LocationService,
        private companyService: CompanyService,
        private currentUserService: CurrentUserService,
        private chosenLocation: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.getParameter();
        this.getCurrentUser();
    }

    getCurrentUser(): void {
        this.currentUserService.getCurrentUser()
            .subscribe((value) => {
                this.user = value;
            });
    }

    getParameter(): void {
        this.route.params
            .subscribe(
                (params) => {
                    this.getLocation(+params['id']);
                },
            );
    }

    getLocation(id: number): void {
        this.locationService.getLocationById(id)
            .subscribe((location) => {
                    this.location = location;
                    this.chosenLocation.location = location;
                    this.getCompany(location.company.id);
                },
            );
    }

    getCompany(id: number): void {
        this.companyService.getCompanyById(id)
            .subscribe((value) => {
                    this.company = value;
                    this.chosenLocation.company = value;
                },
            );
    }

    isFromCompany(): boolean {
        if (this.user === null || this.user === undefined || this.company === null || this.company === undefined) {
            return false;
        }
        let res = false;
        this.user.companies.forEach((value) => {
            if (value.id === this.company.id) {
                res = true;
            }
        });
        return res;
    }

}
