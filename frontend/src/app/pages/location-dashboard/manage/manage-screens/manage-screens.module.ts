import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {ManageScreensRoutingModule} from './manage-screens-routing.module';
import {ManageScreensComponent} from './manage-screens.component';
import {ScreenDetailComponent} from './screen-detail/screen-detail.component';

@NgModule({
    declarations: [ManageScreensComponent, ScreenDetailComponent],
    imports: [
        CommonModule,
        UiModule,
        ManageScreensRoutingModule,
    ],
})
export class ManageScreensModule {
}
