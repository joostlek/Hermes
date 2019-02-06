import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: 'images',
        loadChildren: './manage-images/manage-images.module#ManageImagesModule',
    },
    {
        path: 'screens',
        loadChildren: './manage-screens/manage-screens.module#ManageScreensModule',
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ManageRoutingModule {
}
