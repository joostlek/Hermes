import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PromotionAddComponent} from "@app/promotions/promotion-add/promotion-add.component";

const routes: Routes = [
  {path: '', component: PromotionAddComponent},
  ]
;

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionAddRoutingModule { }
