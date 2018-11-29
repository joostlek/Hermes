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
                children: [
                    {
                        path: ':id',
                        loadChildren: './user/user.module#UserModule',
                    },
                    {
                        path: '',
                        loadChildren: './users/users.module#UsersModule',
                    },
                ],
            },
            {
                path: 'images',
                loadChildren: './images/images.module#ImagesModule',
            },
            {
                path: 'locations',
                children: [
                    {
                        path: ':id',
                        loadChildren: './location/location.module#LocationModule',
                    },
                    {
                        path: '',
                        loadChildren: './locations/locations.module#LocationsModule',
                    },
                ],
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
