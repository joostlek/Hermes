import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {CManageAdvertisingLocationsRoutingModule} from './c-manage-advertising-locations-routing.module';
import {CManageAdvertisingLocationsComponent} from './c-manage-advertising-locations.component';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {AddAdvertisingLocationModalComponent} from './add-advertising-location-modal/add-advertising-location-modal.component';
import {ReactiveFormsModule} from '@angular/forms';
import {RemoveAdvertisingLocationModalComponent} from './remove-advertising-location-modal/remove-advertising-location-modal.component';

@NgModule({
    declarations: [CManageAdvertisingLocationsComponent, AddAdvertisingLocationModalComponent, RemoveAdvertisingLocationModalComponent],
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
