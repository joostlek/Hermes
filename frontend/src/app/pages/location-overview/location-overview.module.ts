import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {LocationListItemComponent} from './location-list-item/location-list-item.component';
import {LocationListComponent} from './location-list/location-list.component';
import {LocationOverviewRoutingModule} from './location-overview-routing.module';
import {LocationOverviewComponent} from './location-overview.component';

@NgModule({
    declarations: [LocationOverviewComponent, LocationListItemComponent, LocationListComponent],
    imports: [
        CommonModule,
        ClarityModule,
        LocationOverviewRoutingModule,
    ],
})
export class LocationOverviewModule {
}
