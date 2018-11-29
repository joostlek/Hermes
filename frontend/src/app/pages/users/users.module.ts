import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {UiModule} from '../../@ui/ui.module';
import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './users.component';

@NgModule({
    declarations: [UsersComponent],
    imports: [
        CommonModule,
        DataModule,
        ClarityModule,
        UiModule,
        UsersRoutingModule,
    ],
})
export class UsersModule {
}
