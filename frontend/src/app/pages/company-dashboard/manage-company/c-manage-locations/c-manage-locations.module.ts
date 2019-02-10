import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {CManageLocationsRoutingModule} from './c-manage-locations-routing.module';
import {CManageLocationsComponent} from './c-manage-locations.component';
import {UiModule} from '../../../../@ui/ui.module';

@NgModule({
    declarations: [CManageLocationsComponent],
    imports: [
        CommonModule,
        UiModule,
        CManageLocationsRoutingModule,
    ],
})
export class CManageLocationsModule {
}
