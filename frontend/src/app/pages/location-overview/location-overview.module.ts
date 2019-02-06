import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {CompanyListComponent} from './company-list/company-list.component';
import {LocationListItemComponent} from './location-list-item/location-list-item.component';
import {LocationOverviewRoutingModule} from './location-overview-routing.module';
import {LocationOverviewComponent} from './location-overview.component';

@NgModule({
    declarations: [LocationOverviewComponent, LocationListItemComponent, CompanyListComponent],
    imports: [
        CommonModule,
        ClarityModule,
        LocationOverviewRoutingModule,
    ],
})
export class LocationOverviewModule {
}
