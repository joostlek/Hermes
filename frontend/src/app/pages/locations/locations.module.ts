import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {UiModule} from '../../@ui/ui.module';
import {LocationsRoutingModule} from './locations-routing.module';
import {LocationsComponent} from './locations.component';
import {CreateLocationWizardComponent} from './create-location-wizard/create-location-wizard.component';

@NgModule({
    declarations: [LocationsComponent, CreateLocationWizardComponent],
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
