import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {Company} from '../../../../@core/data/domain/company';
import {Location} from '../../../../@core/data/domain/location';
import {ChosenLocationService} from '../../chosen-location.service';

@Component({
    selector: 'app-l-manage-advertising-companies',
    templateUrl: './l-manage-advertising-companies.component.html',
    styleUrls: ['./l-manage-advertising-companies.component.css'],
})
export class LManageAdvertisingCompaniesComponent implements OnInit {
    companies: Company[];

    removeCompanyModal: Subject<boolean> = new Subject<boolean>();
    refreshCompanyStream: Subject<boolean> = new Subject<boolean>();

    companyToBeDeleted: Company;

    constructor(
        private chosenLocationService: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.watchRefresh();
        this.refreshCompanyStream.next(true);
    }

    private watchRefresh(): void {
        this.refreshCompanyStream.subscribe(
            () => {
                this.companyToBeDeleted = undefined;
                this.getCompanies();
            },
        );
    }

    private getCompanies(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location: Location) => {
                    this.companies = location.advertisingCompanies;
                },
            );
    }

    public openRemoveCompanyModal(company: Company): void {
        this.companyToBeDeleted = company;
        this.removeCompanyModal.next(true);
    }

}
