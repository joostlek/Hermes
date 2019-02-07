import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LocationDashboardComponent} from './location-dashboard.component';

const routes: Routes = [
    {
        path: '',
        component: LocationDashboardComponent,
        children: [
            {
                path: 'overview',
                loadChildren: './../location/location.module#LocationModule',
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
