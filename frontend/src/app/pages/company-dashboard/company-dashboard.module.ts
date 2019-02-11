import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {CompanyDashboardRoutingModule} from './company-dashboard-routing.module';
import {CompanyDashboardComponent} from './company-dashboard.component';
import {CompanyDetailComponent} from './company-detail/company-detail.component';
import {EditCompanyModalComponent} from './edit-company-modal/edit-company-modal.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [CompanyDashboardComponent, CompanyDetailComponent, EditCompanyModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ClrFormsModule,
        ReactiveFormsModule,
        CompanyDashboardRoutingModule,
    ],
})
export class CompanyDashboardModule {
}
