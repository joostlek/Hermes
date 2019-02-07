import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {PromoteImagesRoutingModule} from './promote-images-routing.module';
import {PromoteImagesComponent} from './promote-images.component';
import {ImageWizardComponent} from './image-wizard/image-wizard.component';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [PromoteImagesComponent, ImageDetailComponent, ImageWizardComponent],
    imports: [
        CommonModule,
        UiModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        PromoteImagesRoutingModule,
    ],
})
export class PromoteImagesModule {
}
