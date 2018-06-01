import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LocationsComponent} from "@app/locations/locations.component";
import {LocationComponent} from "@app/locations/location/location.component";

const routes: Routes = [
  {path: '', component: LocationsComponent},
  {path: 'add', loadChildren: './location-add/location-add.module#LocationAddModule'},
  {path: ':id', component: LocationComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocationsRoutingModule { }
