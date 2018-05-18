import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'images', loadChildren: './images/images.module#ImagesModule'},
  {path: 'promotions', loadChildren: './promotions/promotions.module#PromotionsModule'},
  {path: 'locations', loadChildren: './locations/locations.module#LocationsModule'},
  {path: 'types', loadChildren: './types/types.module#TypesModule'},
  {path: 'auth', loadChildren: './auth/auth.module#AuthModule'},
  {path: 'users', loadChildren: './users/users.module#UsersModule'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
