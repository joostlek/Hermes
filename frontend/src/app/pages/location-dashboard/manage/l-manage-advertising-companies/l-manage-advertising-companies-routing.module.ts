import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LManageAdvertisingCompaniesComponent} from './l-manage-advertising-companies.component';

const routes: Routes = [
    {
        path: '',
        component: LManageAdvertisingCompaniesComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class LManageAdvertisingCompaniesRoutingModule {
}
