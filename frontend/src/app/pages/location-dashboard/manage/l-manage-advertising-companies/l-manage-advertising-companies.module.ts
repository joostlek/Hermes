import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {LManageAdvertisingCompaniesRoutingModule} from './l-manage-advertising-companies-routing.module';
import {LManageAdvertisingCompaniesComponent} from './l-manage-advertising-companies.component';
import {UiModule} from '../../../../@ui/ui.module';

@NgModule({
    declarations: [LManageAdvertisingCompaniesComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UiModule,
        LManageAdvertisingCompaniesRoutingModule,
    ],
})
export class LManageAdvertisingCompaniesModule {
}
