import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {CManageUsersRoutingModule} from './c-manage-users-routing.module';
import {CManageUsersComponent} from './c-manage-users.component';

@NgModule({
    declarations: [CManageUsersComponent],
    imports: [
        CommonModule,
        UiModule,
        CManageUsersRoutingModule,
    ],
})
export class CManageUsersModule {
}
