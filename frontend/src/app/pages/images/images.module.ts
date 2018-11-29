import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ImagesRoutingModule} from './images-routing.module';
import {ImagesComponent} from './images.component';
import {ClarityModule} from '@clr/angular';
import {UiModule} from '../../@ui/ui.module';

@NgModule({
    declarations: [ImagesComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UiModule,
        ImagesRoutingModule,
    ],
})
export class ImagesModule {
}
