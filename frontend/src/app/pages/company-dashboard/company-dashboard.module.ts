import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {CompanyDashboardRoutingModule} from './company-dashboard-routing.module';
import {CompanyDashboardComponent} from './company-dashboard.component';
import {CompanyDetailComponent} from './company-detail/company-detail.component';

@NgModule({
    declarations: [CompanyDashboardComponent, CompanyDetailComponent],
    imports: [
        CommonModule,
        ClarityModule,
        CompanyDashboardRoutingModule,
    ],
})
export class CompanyDashboardModule {
}
