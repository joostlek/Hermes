import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'images', loadChildren: './images/images.module#ImagesModule'},
  {path: 'promotions', loadChildren: './promotions/promotions.module#PromotionsModule'},
  {path: 'types', loadChildren: './types/types.module#TypesModule'},
  {path: 'auth', loadChildren: './auth/auth.module#AuthModule'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
