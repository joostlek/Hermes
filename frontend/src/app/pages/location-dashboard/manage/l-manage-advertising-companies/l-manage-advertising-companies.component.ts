import {Component, OnInit} from '@angular/core';
import {ChosenLocationService} from '../../chosen-location.service';
import {Company} from '../../../../@core/data/domain/company';
import {Location} from '../../../../@core/data/domain/location';

@Component({
    selector: 'app-l-manage-advertising-companies',
    templateUrl: './l-manage-advertising-companies.component.html',
    styleUrls: ['./l-manage-advertising-companies.component.css'],
})
export class LManageAdvertisingCompaniesComponent implements OnInit {
    companies: Company[];

    constructor(
        private chosenLocationService: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.getLocations();
    }

    private getLocations(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location: Location) => {
                    this.companies = location.advertisingCompanies;
                },
            );
    }

}
