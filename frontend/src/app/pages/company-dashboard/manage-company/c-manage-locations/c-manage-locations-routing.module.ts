import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CManageLocationsComponent} from './c-manage-locations.component';

const routes: Routes = [
    {
        path: '',
        component: CManageLocationsComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CManageLocationsRoutingModule {
}
