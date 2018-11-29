import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';
import {ScreensRoutingModule} from './screens-routing.module';
import {ScreensComponent} from './screens.component';
import {UiModule} from '../../@ui/ui.module';

@NgModule({
    declarations: [ScreensComponent],
    imports: [
        CommonModule,
        ClarityModule,
        UiModule,
        DataModule,
        ScreensRoutingModule,
    ],
})
export class ScreensModule {
}
