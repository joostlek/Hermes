import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PromotionsComponent} from "./promotions.component";
import {PromotionDetailComponent} from "@app/promotions/promotion-detail/promotion-detail.component";

const routes: Routes = [
  {path: '', component: PromotionsComponent},
  {path: 'add', loadChildren: './promotion-add/promotion-add.module#PromotionAddModule'},
  {path: ":id", component: PromotionDetailComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionsRoutingModule { }
