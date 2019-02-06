import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {LocationDashboardRoutingModule} from './location-dashboard-routing.module';
import {LocationDashboardComponent} from './location-dashboard.component';
import {ChosenLocationService} from './chosen-location.service';

@NgModule({
    declarations: [LocationDashboardComponent],
    imports: [
        CommonModule,
        ClarityModule,
        LocationDashboardRoutingModule,
    ],
    providers: [
        ChosenLocationService,
    ],
})
export class LocationDashboardModule {
}
