import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LocationDashboardComponent} from './location-dashboard.component';
import {LocationDetailComponent} from './location-detail/location-detail.component';

const routes: Routes = [
    {
        path: '',
        component: LocationDashboardComponent,
        children: [
            {
                path: 'overview',
                component: LocationDetailComponent,
            },
            {
                path: 'manage',
                loadChildren: './manage/manage.module#ManageModule',
            },
            {
                path: 'advertise',
                loadChildren: './promote/promote.module#PromoteModule',
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class LocationDashboardRoutingModule {
}
