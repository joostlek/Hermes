import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CManageAdvertisingLocationsComponent} from './c-manage-advertising-locations.component';

const routes: Routes = [
    {
        path: '',
        component: CManageAdvertisingLocationsComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CManageAdvertisingLocationsRoutingModule {
}
