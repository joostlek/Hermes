import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: 'images',
        loadChildren: './l-manage-images/l-manage-images.module#LManageImagesModule',
    },
    {
        path: 'screens',
        loadChildren: './l-manage-screens/l-manage-screens.module#LManageScreensModule',
    },
    {
        path: 'advertising-companies',
        loadChildren: './l-manage-advertising-companies/l-manage-advertising-companies.module#LManageAdvertisingCompaniesModule',
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ManageRoutingModule {
}
