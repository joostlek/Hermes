import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {PersonalPageRoutingModule} from './personal-page-routing.module';
import {PersonalPageComponent} from './personal-page.component';

@NgModule({
    declarations: [PersonalPageComponent],
    imports: [
        CommonModule,
        PersonalPageRoutingModule,
    ],
})
export class PersonalPageModule {
}
