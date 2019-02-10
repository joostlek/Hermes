import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {CompanyListItemComponent} from './company-list-item/company-list-item.component';
import {CompanyListComponent} from './company-list/company-list.component';
import {CompanyOverviewRoutingModule} from './company-overview-routing.module';
import {CompanyOverviewComponent} from './company-overview.component';
import {ChosenCompanyService} from '../company-dashboard/chosen-company.service';
import {CompanyWizardComponent} from './company-wizard/company-wizard.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [CompanyOverviewComponent, CompanyListComponent, CompanyListItemComponent, CompanyWizardComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        CompanyOverviewRoutingModule,
    ],
    providers: [
        ChosenCompanyService,
    ],
})
export class CompanyOverviewModule {
}
