import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LocationOverviewComponent} from './location-overview.component';

const routes: Routes = [
    {
        path: '',
        component: LocationOverviewComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class LocationOverviewRoutingModule {
}
