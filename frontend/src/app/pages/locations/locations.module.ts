import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsNextModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {UiModule} from '../../@ui/ui.module';
import {LocationsRoutingModule} from './locations-routing.module';
import {LocationsComponent} from './locations.component';

@NgModule({
    declarations: [LocationsComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        UiModule,
        ReactiveFormsModule,
        ClrFormsNextModule,

        LocationsRoutingModule,
    ],
})
export class LocationsModule {
}
