import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {UiModule} from '../../@ui/ui.module';
import {UserRoutingModule} from './user-routing.module';
import {UserComponent} from './user.component';

@NgModule({
    declarations: [UserComponent],
    imports: [
        CommonModule,
        DataModule,
        UiModule,
        ClarityModule,
        UserRoutingModule,
    ],
})
export class UserModule {
}
