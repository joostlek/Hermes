import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {EditUserModalComponent} from './edit-user-modal/edit-user-modal.component';
import {PersonalPageRoutingModule} from './personal-page-routing.module';
import {PersonalPageComponent} from './personal-page.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [PersonalPageComponent, EditUserModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        PersonalPageRoutingModule,
    ],
})
export class PersonalPageModule {
}
