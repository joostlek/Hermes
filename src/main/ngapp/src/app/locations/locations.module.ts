import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocationsRoutingModule } from './locations-routing.module';
import { LocationsComponent } from './locations.component';
import { LocationTableComponent } from './location-table/location-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    LocationsRoutingModule,
  ],
  declarations: [LocationsComponent, LocationTableComponent]
})
export class LocationsModule { }
