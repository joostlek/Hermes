import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './users.component';
import {UserTableComponent} from './user-table/user-table.component';
import {ClarityModule} from '@clr/angular';

@NgModule({
    declarations: [UsersComponent, UserTableComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UsersRoutingModule
    ]
})
export class UsersModule {
}
