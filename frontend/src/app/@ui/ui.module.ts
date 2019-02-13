import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {ExampleScreenComponent} from './components/example-screen/example-screen.component';
import {SecuredImageComponent} from './components/secured-image/secured-image.component';
import {Sidebar, StandardLayout} from './layout';
import {EditImageModalComponent} from './modals/edit-image-modal/edit-image-modal.component';
import {RemoveImageModalComponent} from './modals/remove-image-modal/remove-image-modal.component';
import {RemoveLocationModalComponent} from './modals/remove-location-modal/remove-location-modal.component';
import {ImageTableComponent} from './tables/image-table/image-table.component';
import {LocationTableComponent} from './tables/location-table/location-table.component';
import {ScreenTableComponent} from './tables/screen-table/screen-table.component';
import {UserTableComponent} from './tables/user-table/user-table.component';

const COMPONENTS = [
    Sidebar,
    StandardLayout,
    ScreenTableComponent,
    ImageTableComponent,
    LocationTableComponent,
    RemoveLocationModalComponent,
    UserTableComponent,
    SecuredImageComponent,
    ExampleScreenComponent,
    EditImageModalComponent,
    RemoveImageModalComponent,
];

@NgModule({
    declarations: [...COMPONENTS],
    imports: [
        CommonModule,
        ClarityModule,
        ReactiveFormsModule,
        ClrFormsModule,
        RouterModule,
    ],
    exports: [...COMPONENTS],
})
export class UiModule {
}
