import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './users.component';
import {DataModule} from '../../@core/data/data.module';

@NgModule({
    declarations: [UsersComponent],
    imports: [
        CommonModule,
        DataModule,
        ClarityModule,
        UsersRoutingModule,
    ],
})
export class UsersModule {
}
