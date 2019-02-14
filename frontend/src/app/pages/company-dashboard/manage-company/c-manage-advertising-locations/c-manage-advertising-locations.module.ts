import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {CManageAdvertisingLocationsRoutingModule} from './c-manage-advertising-locations-routing.module';
import {CManageAdvertisingLocationsComponent} from './c-manage-advertising-locations.component';

@NgModule({
    declarations: [CManageAdvertisingLocationsComponent],
    imports: [
        CommonModule,
        CManageAdvertisingLocationsRoutingModule,
    ],
})
export class CManageAdvertisingLocationsModule {
}
