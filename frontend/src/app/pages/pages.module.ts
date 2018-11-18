import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ClarityModule} from '@clr/angular';
import {UiModule} from '../@ui/ui.module';
import {PagesRoutingModule} from './pages-routing.module';
import {PagesComponent} from './pages.component';

@NgModule({
    declarations: [PagesComponent],
    imports: [
        CommonModule,
        UiModule,
        ClarityModule,
        PagesRoutingModule,
    ],
})
export class PagesModule {
}
