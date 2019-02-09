import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {ManageImagesRoutingModule} from './manage-images-routing.module';
import {ManageImagesComponent} from './manage-images.component';

@NgModule({
    declarations: [ManageImagesComponent, ImageDetailComponent],
    imports: [
        CommonModule,
        UiModule,
        ManageImagesRoutingModule,
    ],
})
export class ManageImagesModule {
}
