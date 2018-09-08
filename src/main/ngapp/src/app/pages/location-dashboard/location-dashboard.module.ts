import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocationDashboardRoutingModule } from './location-dashboard-routing.module';
import { LocationDashboardComponent } from './location-dashboard.component';

@NgModule({
  imports: [
    CommonModule,
    LocationDashboardRoutingModule,
  ],
  declarations: [LocationDashboardComponent],
})
export class LocationDashboardModule { }
