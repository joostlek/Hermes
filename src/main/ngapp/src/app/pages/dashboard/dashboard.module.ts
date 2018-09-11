import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { StatCardComponent } from './stat-card/stat-card.component';
import {ThemeModule} from '../../@theme/theme.module';
import {NbButtonModule} from '@nebular/theme';

@NgModule({
  imports: [
    CommonModule,
    ThemeModule,
    NbButtonModule,
    DashboardRoutingModule
  ],
  declarations: [DashboardComponent, StatCardComponent]
})
export class DashboardModule { }
