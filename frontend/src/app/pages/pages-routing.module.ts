import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PagesComponent} from './pages.component';

const routes: Routes = [
    {
        path: '',
        component: PagesComponent,
        children: [
            {
                path: 'users',
                loadChildren: './users/users.module#UsersModule',
            },
            {
                path: 'images',
                loadChildren: './images/images.module#ImagesModule',
            },
            {
                path: 'locations/:id',
                loadChildren: './location/location.module#LocationModule',
            },
            {
                path: 'locations',
                loadChildren: './locations/locations.module#LocationsModule',
            },
            {
                path: 'screens',
                loadChildren: './screens/screens.module#ScreensModule',
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
