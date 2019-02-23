import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LManageScreensComponent} from './l-manage-screens.component';
import {ScreenDetailComponent} from './screen-detail/screen-detail.component';

const routes: Routes = [
    {
        path: ':screenId',
        component: ScreenDetailComponent,
    },
    {
        path: '',
        component: LManageScreensComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class LManageScreensRoutingModule {
}
