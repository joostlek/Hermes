import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ClarityModule} from '@clr/angular';
import {Sidebar, StandardLayout} from './layout';
import {ScreenTableComponent} from './tables/screen-table/screen-table.component';
import {ImageTableComponent} from './tables/image-table/image-table.component';
import {LocationTableComponent} from './tables/location-table/location-table.component';
import {UserTableComponent} from './tables/user-table/user-table.component';
import {RouterModule} from '@angular/router';
import {RemoveLocationModalComponent} from './modals/remove-location-modal/remove-location-modal.component';

const COMPONENTS = [
    Sidebar,
    StandardLayout,
    ScreenTableComponent,
    ImageTableComponent,
    LocationTableComponent,
    RemoveLocationModalComponent,
    UserTableComponent,
];

@NgModule({
    declarations: [...COMPONENTS],
    imports: [
        CommonModule,
        ClarityModule,
        RouterModule,
    ],
    exports: [...COMPONENTS],
})
export class UiModule {
}
