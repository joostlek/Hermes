import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {CManageUsersRoutingModule} from './c-manage-users-routing.module';
import {CManageUsersComponent} from './c-manage-users.component';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {AddUserModalComponent} from './add-user-modal/add-user-modal.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [CManageUsersComponent, AddUserModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        UiModule,
        CManageUsersRoutingModule,
    ],
})
export class CManageUsersModule {
}
