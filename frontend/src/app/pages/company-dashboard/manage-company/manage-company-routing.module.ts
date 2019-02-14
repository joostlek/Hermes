import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: 'locations',
        loadChildren: './c-manage-locations/c-manage-locations.module#CManageLocationsModule',
    },
    {
        path: 'advertising-locations',
        loadChildren: './c-manage-advertising-locations/c-manage-advertising-locations.module#CManageAdvertisingLocationsModule',
    },
    {
        path: 'users',
        loadChildren: './c-manage-users/c-manage-users.module#CManageUsersModule',
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ManageCompanyRoutingModule {
}
