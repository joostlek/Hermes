import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ImagesRoutingModule} from './images-routing.module';
import {ImagesComponent} from './images.component';
import {ClarityModule, ClrFormsNextModule} from '@clr/angular';
import {UiModule} from '../../@ui/ui.module';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [ImagesComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsNextModule,
        UiModule,
        ImagesRoutingModule,
    ],
})
export class ImagesModule {
}
