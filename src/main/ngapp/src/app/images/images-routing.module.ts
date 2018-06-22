import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ImagesComponent} from "./images.component";
import {ImageComponent} from "./image/image.component";
import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {Roles} from "@app/_models/roles";
import {AllowimagesComponent} from "@app/images/allowimages/allowimages.component";

const routes: Routes = [
  {
    path: '',
    component: ImagesComponent,
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_ADVERTISING
      ]
    }
  },
  {
    path: 'allow',
    component: AllowimagesComponent,
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_OWNER
      ]
    }
  },
  {
    path: 'add',
    loadChildren: './image-add/image-add.module#ImageAddModule',
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_OWNER
      ]
    }
  },
  {
    path: ':id',
    component: ImageComponent,
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_OWNER,
        Roles.ROLE_ADVERTISING
      ]
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImagesRoutingModule { }
