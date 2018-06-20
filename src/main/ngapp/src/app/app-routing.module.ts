import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {Roles} from "@app/_models/roles";

const routes: Routes = [
  {
    path: 'images',
    loadChildren: './images/images.module#ImagesModule'
  },
  {
    path: 'promotions',
    loadChildren: './promotions/promotions.module#PromotionsModule'
  },
  {
    path: 'locations',
    loadChildren: './locations/locations.module#LocationsModule'
  },
  {
    path: 'types',
    loadChildren: './types/types.module#TypesModule'
  },
  {
    path: 'auth',
    loadChildren: './auth/auth.module#AuthModule'
  },
  {
    path: 'users',
    loadChildren: './users/users.module#UsersModule'
  },
  {
    path: 'screens',
    loadChildren: './screens/screens.module#ScreensModule',
    canActivate: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_SUPERUSER,
      ]
    }
  },
  {
    path: 'register',
    loadChildren: './register/register.module#RegisterModule',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
