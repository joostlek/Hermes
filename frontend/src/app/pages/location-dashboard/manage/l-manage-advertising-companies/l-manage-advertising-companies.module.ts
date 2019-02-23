import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {LManageAdvertisingCompaniesRoutingModule} from './l-manage-advertising-companies-routing.module';
import {LManageAdvertisingCompaniesComponent} from './l-manage-advertising-companies.component';
import {RemoveAdvertisingCompanyModalComponent} from './remove-advertising-company-modal/remove-advertising-company-modal.component';

@NgModule({
    declarations: [LManageAdvertisingCompaniesComponent, RemoveAdvertisingCompanyModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        LManageAdvertisingCompaniesRoutingModule,
    ],
})
export class LManageAdvertisingCompaniesModule {
}
