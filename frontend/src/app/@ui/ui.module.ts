import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ClarityModule} from '@clr/angular';
import {Sidebar, StandardLayout} from './layout';

const COMPONENTS = [
    Sidebar,
    StandardLayout,
];

@NgModule({
    declarations: [StandardLayout, Sidebar],
    imports: [
        CommonModule,
        ClarityModule,
    ],
    exports: [...COMPONENTS],
})
export class UiModule {
}
