import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {LocationTableComponent} from './location-table/location-table.component';
import {LocationsRoutingModule} from './locations-routing.module';
import {LocationsComponent} from './locations.component';

@NgModule({
    declarations: [LocationsComponent, LocationTableComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        LocationsRoutingModule,
    ],
})
export class LocationsModule {
}
