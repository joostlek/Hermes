import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScreensRoutingModule } from './screens-routing.module';
import { ScreensComponent } from './screens.component';
import { ScreenTableComponent } from './screen-table/screen-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    ScreensRoutingModule,
  ],
  declarations: [ScreensComponent, ScreenTableComponent]
})
export class ScreensModule { }
