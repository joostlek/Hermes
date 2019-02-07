import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {PromoteImagesRoutingModule} from './promote-images-routing.module';
import {PromoteImagesComponent} from './promote-images.component';

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
