import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LocationsComponent} from "@app/locations/locations.component";
import {LocationComponent} from "@app/locations/location/location.component";
import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {AuthGuardService as AuthGuard} from "@app/_services/auth-guard.service";
import {Roles} from "@app/_models/roles";


const routes: Routes = [
  {
    path: '',
    component: LocationsComponent,
    canLoad: [AuthGuard]
  },
  {
    path: 'add',
    loadChildren: './location-add/location-add.module#LocationAddModule',
    canLoad: [AuthGuard]
  },
  {
    path: ':id',
    component: LocationComponent,
    canLoad: [RoleGuard],
    data: {
      roles: [
        Roles.ROLE_OWNER
      ]
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocationsRoutingModule { }
