import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ScreensRoutingModule} from './screens-routing.module';
import {ScreensComponent} from './screens.component';
import {ScreenTableComponent} from './screen-table/screen-table.component';
import {ClarityModule} from '@clr/angular';
import {DataModule} from '../../@core/data/data.module';

@NgModule({
    declarations: [ScreensComponent, ScreenTableComponent],
    imports: [
        CommonModule,
        ClarityModule,
        DataModule,
        ScreensRoutingModule
    ],
    exports: [
        ScreenTableComponent,
    ]
})
export class ScreensModule {
}
