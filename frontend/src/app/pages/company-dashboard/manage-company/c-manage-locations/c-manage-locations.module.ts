import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {CManageLocationsRoutingModule} from './c-manage-locations-routing.module';
import {CManageLocationsComponent} from './c-manage-locations.component';
import {CreateLocationWizardComponent} from './create-location-wizard/create-location-wizard.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [CManageLocationsComponent, CreateLocationWizardComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ClrFormsModule,
        ReactiveFormsModule,
        CManageLocationsRoutingModule,
    ],
})
export class CManageLocationsModule {
}
