import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LocationsComponent} from "@app/locations/locations.component";

const routes: Routes = [
  {path: '', component: LocationsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocationsRoutingModule { }
