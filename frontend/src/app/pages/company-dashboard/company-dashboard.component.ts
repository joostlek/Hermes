import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Company} from '../../@core/data/domain/company';
import {User} from '../../@core/data/domain/user';
import {ChosenCompanyService} from './chosen-company.service';

@Component({
    selector: 'app-company-dashboard',
    templateUrl: './company-dashboard.component.html',
    styleUrls: ['./company-dashboard.component.css'],
})
export class CompanyDashboardComponent implements OnInit {
    admin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);
    user: User;
    company: Company;

    constructor(
        private companyService: CompanyService,
        private chosenCompanyService: ChosenCompanyService,
        private route: ActivatedRoute,
        private currentUserService: CurrentUserService,
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
                this.getUser();
                },
            );
    }

    private getUser(): void {
        this.currentUserService.getCurrentUser()
            .subscribe(
                (user: User) => {
                    this.user = user;
                    this.checkRights();
                },
            );
    }

    public checkRights(): void {
        let res = false;
        this.company.users.forEach(
            (user: User) => {
                if (user.id === this.user.id) {
                    res = true;
                }
            },
        );
        this.admin.next(res);
    }

}
