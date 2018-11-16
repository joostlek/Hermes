import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Sidebar, StandardLayout} from './layout';
import {ClarityModule} from '@clr/angular';

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
    exports: [...COMPONENTS,]
})
export class UiModule {
}
