import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {CompanyListItemComponent} from './company-list-item/company-list-item.component';
import {CompanyListComponent} from './company-list/company-list.component';
import {CompanyOverviewRoutingModule} from './company-overview-routing.module';
import {CompanyOverviewComponent} from './company-overview.component';
import {ChosenCompanyService} from '../company-dashboard/chosen-company.service';

@NgModule({
    declarations: [CompanyOverviewComponent, CompanyListComponent, CompanyListItemComponent],
    imports: [
        CommonModule,
        ClarityModule,
        CompanyOverviewRoutingModule,
    ],
    providers: [
        ChosenCompanyService,
    ],
})
export class CompanyOverviewModule {
}
