import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {AuthGuardService as AuthGuard} from "@app/_services/auth-guard.service";
import {Roles} from "@app/_models/roles";

const routes: Routes = [
  {
    path: 'images',
    loadChildren: './images/images.module#ImagesModule',
    canLoad: [AuthGuard]
  },
  {
    path: 'promotions',
    loadChildren: './promotions/promotions.module#PromotionsModule',
    canLoad: [AuthGuard]
  },
  {
    path: 'locations',
    loadChildren: './locations/locations.module#LocationsModule',
    canLoad: [AuthGuard]
  },
  {
    path: 'types',
    loadChildren: './types/types.module#TypesModule',
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_OWNER,
      ]
    }
  },
  {
    path: 'auth',
    loadChildren: './auth/auth.module#AuthModule'
  },
  {
    path: 'users',
    loadChildren: './users/users.module#UsersModule',
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_SUPERUSER,
      ]
    }
  },
  {
    path: 'screens',
    loadChildren: './screens/screens.module#ScreensModule',
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_SUPERUSER,
      ]
    }
  },
  {
    path: 'register',
    loadChildren: './register/register.module#RegisterModule',
  },
  {
    path: 'account',
    loadChildren: './account/account.module#AccountModule',
    canLoad: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
