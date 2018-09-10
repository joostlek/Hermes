import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocationDashboardRoutingModule } from './location-dashboard-routing.module';
import { LocationDashboardComponent } from './location-dashboard.component';
import { ActionsComponent } from './actions/actions.component';
import {ThemeModule} from '../../@theme/theme.module';
import { LocationsComponent } from './locations/locations.component';
import { NbButtonModule } from '@nebular/theme';

@NgModule({
  imports: [
    CommonModule,
    ThemeModule,
    NbButtonModule,
    LocationDashboardRoutingModule,
  ],
  declarations: [LocationDashboardComponent, ActionsComponent, LocationsComponent],
})
export class LocationDashboardModule { }
