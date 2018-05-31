import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PromotionsComponent} from "./promotions.component";
import {PromotionComponent} from "@app/promotions/promotion/promotion.component";

const routes: Routes = [
  {path: '', component: PromotionsComponent},
  {path: 'add', loadChildren: './promotion-add/promotion-add.module#PromotionAddModule'},
  {path: ":id", component: PromotionComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionsRoutingModule { }
