import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {UiModule} from '../../@ui/ui.module';
import {LocationsRoutingModule} from './locations-routing.module';
import {LocationsComponent} from './locations.component';

@NgModule({
    declarations: [LocationsComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UiModule,
        ReactiveFormsModule,
        ClrFormsModule,
        LocationsRoutingModule,
    ],
})
export class LocationsModule {
}
