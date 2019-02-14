import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {CManageAdvertisingLocationsRoutingModule} from './c-manage-advertising-locations-routing.module';
import {CManageAdvertisingLocationsComponent} from './c-manage-advertising-locations.component';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {AddAdvertisingLocationModalComponent} from './add-advertising-location-modal/add-advertising-location-modal.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [CManageAdvertisingLocationsComponent, AddAdvertisingLocationModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ClrFormsModule,
        ReactiveFormsModule,
        CManageAdvertisingLocationsRoutingModule,
    ],
})
export class CManageAdvertisingLocationsModule {
}
