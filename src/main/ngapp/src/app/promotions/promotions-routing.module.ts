import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PromotionsComponent} from "./promotions.component";
import {PromotionComponent} from "@app/promotions/promotion/promotion.component";
import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {AuthGuardService as AuthGuard} from "@app/_services/auth-guard.service";
import {Roles} from "@app/_models/roles";

const routes: Routes = [
  {
    path: '',
    component: PromotionsComponent,
    canLoad: [AuthGuard]
  },
  {
    path: 'add',
    loadChildren: './promotion-add/promotion-add.module#PromotionAddModule',
    canLoad: [AuthGuard]
  },
  {
    path: ":id",
    component: PromotionComponent,
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_ADVERTISING,
      ]
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionsRoutingModule { }
