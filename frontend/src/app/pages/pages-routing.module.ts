import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PagesComponent} from './pages.component';

const routes: Routes = [
    {
        path: '',
        component: PagesComponent,
        children: [
            {
                path: 'locations',
                children: [
                    {
                        path: ':id',
                        loadChildren: './location-dashboard/location-dashboard.module#LocationDashboardModule',
                    },
                    {
                        path: '',
                        loadChildren: './location-overview/location-overview.module#LocationOverviewModule',
                    },
                ],
            },
            {
                path: 'companies',
                children: [
                    {
                        path: ':id',
                        loadChildren: './company-dashboard/company-dashboard.module#CompanyDashboardModule',
                    },
                    {
                        path: '',
                        loadChildren: './company-overview/company-overview.module#CompanyOverviewModule',
                    },
                ],
            },
            {
                path: 'profile',
                loadChildren: './personal-page/personal-page.module#PersonalPageModule',
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class PagesRoutingModule {
}
