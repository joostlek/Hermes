import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {LocationsRoutingModule} from './locations-routing.module';
import {LocationsComponent} from './locations.component';
import {UiModule} from '../../@ui/ui.module';

@NgModule({
    declarations: [LocationsComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        UiModule,
        LocationsRoutingModule,
    ],
})
export class LocationsModule {
}
