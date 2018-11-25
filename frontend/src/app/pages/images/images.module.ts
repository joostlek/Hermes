import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ImagesRoutingModule} from './images-routing.module';
import {ImagesComponent} from './images.component';
import {ImageTableComponent} from './image-table/image-table.component';
import {ClarityModule} from '@clr/angular';

@NgModule({
    declarations: [ImagesComponent, ImageTableComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ImagesRoutingModule,
    ],
})
export class ImagesModule {
}
