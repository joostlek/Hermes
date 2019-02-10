import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompanyDashboardComponent} from './company-dashboard.component';
import {CompanyDetailComponent} from './company-detail/company-detail.component';

const routes: Routes = [
    {
        path: '',
        component: CompanyDashboardComponent,
        children: [
            {
                path: 'overview',
                component: CompanyDetailComponent,
            },
            {
                path: 'manage',
                loadChildren: './manage-company/manage-company.module#ManageCompanyModule',
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CompanyDashboardRoutingModule {
}
