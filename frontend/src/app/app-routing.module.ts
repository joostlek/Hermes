import {NgModule} from '@angular/core';
import {ExtraOptions, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: 'auth',
        loadChildren: './auth/auth.module#AuthModule',
    },
    {
        path: '',
        loadChildren: './pages/pages.module#PagesModule',
    },
];

const config: ExtraOptions = {
    useHash: true,
};

@NgModule({
    imports: [
        RouterModule.forRoot(routes),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
