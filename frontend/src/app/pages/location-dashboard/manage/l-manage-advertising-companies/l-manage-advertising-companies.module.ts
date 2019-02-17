import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {LManageAdvertisingCompaniesRoutingModule} from './l-manage-advertising-companies-routing.module';
import {LManageAdvertisingCompaniesComponent} from './l-manage-advertising-companies.component';
import {ClarityModule} from '@clr/angular';

@NgModule({
    declarations: [LManageAdvertisingCompaniesComponent],
    imports: [
        CommonModule,
        ClarityModule,
        LManageAdvertisingCompaniesRoutingModule,
    ],
})
export class LManageAdvertisingCompaniesModule {
}
