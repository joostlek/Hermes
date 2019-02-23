import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ClarityModule} from '@clr/angular';
import {UiModule} from '../../../../@ui/ui.module';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {LManageImagesRoutingModule} from './l-manage-images-routing.module';
import {LManageImagesComponent} from './l-manage-images.component';

@NgModule({
    declarations: [LManageImagesComponent, ImageDetailComponent],
    imports: [
        CommonModule,
        UiModule,
        ClarityModule,
        LManageImagesRoutingModule,
    ],
})
export class LManageImagesModule {
}
