import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {UserTableComponent} from './user-table/user-table.component';
import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './users.component';

@NgModule({
    declarations: [UsersComponent, UserTableComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UsersRoutingModule,
    ],
})
export class UsersModule {
}
