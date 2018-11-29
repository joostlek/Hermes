import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ClarityModule} from '@clr/angular';
import {Sidebar, StandardLayout} from './layout';
import {ScreenTableComponent} from './tables/screen-table/screen-table.component';

const COMPONENTS = [
    Sidebar,
    StandardLayout,
    ScreenTableComponent,
];

@NgModule({
    declarations: [...COMPONENTS],
    imports: [
        CommonModule,
        ClarityModule,
    ],
    exports: [...COMPONENTS],
})
export class UiModule {
}
