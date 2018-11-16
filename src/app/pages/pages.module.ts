import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PagesRoutingModule} from './pages-routing.module';
import {PagesComponent} from './pages.component';
import {ClarityModule} from '@clr/angular';
import {UiModule} from '../@ui/ui.module';

@NgModule({
    declarations: [PagesComponent],
    imports: [
        CommonModule,
        UiModule,
        ClarityModule,
        PagesRoutingModule
    ],
})
export class PagesModule {
}

