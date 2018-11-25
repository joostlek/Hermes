import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PagesComponent} from './pages.component';

const routes: Routes = [
    {
        path: '', component: PagesComponent,
        children: [
            {
                path: 'u',
                loadChildren: './users/users.module#UsersModule',
            },
            {
                path: 'i',
                loadChildren: './images/images.module#ImagesModule',
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class PagesRoutingModule {
}
