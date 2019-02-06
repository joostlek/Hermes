import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ImageDetailComponent} from './image-detail/image-detail.component';
import {ManageImagesComponent} from './manage-images.component';

const routes: Routes = [
    {
        path: ':imageId',
        component: ImageDetailComponent,
    },
    {
        path: '',
        component: ManageImagesComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ManageImagesRoutingModule {
}
