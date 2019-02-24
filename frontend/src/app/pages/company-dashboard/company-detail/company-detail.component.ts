import {Component, OnInit} from '@angular/core';
import {Company} from '../../../@core/data/domain/company';
import {ChosenCompanyService} from '../chosen-company.service';
import {Subject} from 'rxjs';
import {Router} from '@angular/router';

@Component({
    selector: 'app-company-detail',
    templateUrl: './company-detail.component.html',
    styleUrls: ['./company-detail.component.css'],
})
export class CompanyDetailComponent implements OnInit {
    company: Company;

    editCompanyModal: Subject<boolean> = new Subject();
    deleteCompanyModal: Subject<boolean> = new Subject();
    refreshCompany: Subject<boolean> = new Subject();
    forwardToList: Subject<boolean> = new Subject();

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.watchRefresh();
        this.watchList();
        this.getCompany();
    }

    private watchList(): void {
        this.forwardToList.subscribe(
            () => {
                this.router.navigateByUrl('/companies');
            },
        );
    }

    private watchRefresh(): void {
        this.refreshCompany.subscribe(
            () => {
                this.getCompany();
            },
        );
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company: Company) => {
                    this.company = company;
                },
            );
    }

    public openEditModal(): void {
        this.editCompanyModal.next(true);
    }

    public openDeleteModal(): void {
        this.deleteCompanyModal.next(true);
    }
}
