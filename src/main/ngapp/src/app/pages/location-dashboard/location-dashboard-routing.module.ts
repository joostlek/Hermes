import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LocationDashboardComponent} from './location-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: LocationDashboardComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LocationDashboardRoutingModule { }
