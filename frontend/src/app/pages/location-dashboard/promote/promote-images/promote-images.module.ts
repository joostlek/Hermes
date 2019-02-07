import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {PromoteImagesRoutingModule} from './promote-images-routing.module';
import {PromoteImagesComponent} from './promote-images.component';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {UiModule} from '../../../../@ui/ui.module';

@NgModule({
    declarations: [PromoteImagesComponent, ImageDetailComponent],
    imports: [
        CommonModule,
        UiModule,
        PromoteImagesRoutingModule,
    ],
})
export class PromoteImagesModule {
}
