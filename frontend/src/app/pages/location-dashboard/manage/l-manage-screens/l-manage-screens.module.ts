import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {UiModule} from '../../../../@ui/ui.module';
import {LManageScreensRoutingModule} from './l-manage-screens-routing.module';
import {LManageScreensComponent} from './l-manage-screens.component';
import {ScreenDetailComponent} from './screen-detail/screen-detail.component';

@NgModule({
    declarations: [LManageScreensComponent, ScreenDetailComponent],
    imports: [
        CommonModule,
        UiModule,
        LManageScreensRoutingModule,
    ],
})
export class LManageScreensModule {
}
