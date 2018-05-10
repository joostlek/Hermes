import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'images', loadChildren: './images/images.module#ImagesModule'},
  {path: 'promotions', loadChildren: './promotions/promotions.module#PromotionsModule'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
