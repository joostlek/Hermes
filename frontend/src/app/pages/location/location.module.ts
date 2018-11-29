import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LocationRoutingModule} from './location-routing.module';
import {LocationComponent} from './location.component';
import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {UiModule} from '../../@ui/ui.module';

@NgModule({
    declarations: [LocationComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        UiModule,
        LocationRoutingModule,
    ],
})
export class LocationModule {
}
