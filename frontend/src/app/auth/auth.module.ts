import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../@core/data/data.module';
import {UiModule} from '../@ui/ui.module';
import {AuthRoutingModule} from './auth-routing.module';
import {AuthComponent} from './auth.component';
import {LoginComponent} from './login/login.component';
import {FormsModule} from '@angular/forms';
import {LogoutComponent} from './logout/logout.component';

@NgModule({
    declarations: [AuthComponent, LoginComponent, LogoutComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        FormsModule,
        UiModule,
        AuthRoutingModule,
    ],
})
export class AuthModule {
}