import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {AddUserModalComponent} from './add-user-modal/add-user-modal.component';
import {CManageUsersRoutingModule} from './c-manage-users-routing.module';
import {CManageUsersComponent} from './c-manage-users.component';
import { RemoveUserModalComponent } from './remove-user-modal/remove-user-modal.component';

@NgModule({
    declarations: [CManageUsersComponent, AddUserModalComponent, RemoveUserModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        CManageUsersRoutingModule,
    ],
})
export class CManageUsersModule {
}
