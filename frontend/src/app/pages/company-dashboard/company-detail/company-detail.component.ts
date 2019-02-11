import {Component, OnInit} from '@angular/core';
import {Company} from '../../../@core/data/domain/company';
import {ChosenCompanyService} from '../chosen-company.service';
import {Subject} from 'rxjs';

@Component({
    selector: 'app-company-detail',
    templateUrl: './company-detail.component.html',
    styleUrls: ['./company-detail.component.css'],
})
export class CompanyDetailComponent implements OnInit {
    company: Company;

    editModal: Subject<boolean> = new Subject();

    constructor(
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.getCompany();
    }

    getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe((company) => {
                    this.company = company;
                },
            );
    }

    public openEditModal(): void {
        this.editModal.next(true);
    }
}
