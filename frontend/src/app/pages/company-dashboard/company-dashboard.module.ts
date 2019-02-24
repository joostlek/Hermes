import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {CompanyDashboardRoutingModule} from './company-dashboard-routing.module';
import {CompanyDashboardComponent} from './company-dashboard.component';
import {CompanyDetailComponent} from './company-detail/company-detail.component';
import {EditCompanyModalComponent} from './edit-company-modal/edit-company-modal.component';
import {RemoveCompanyModalComponent} from './remove-company-modal/remove-company-modal.component';

@NgModule({
    declarations: [CompanyDashboardComponent, CompanyDetailComponent, EditCompanyModalComponent, RemoveCompanyModalComponent],
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
