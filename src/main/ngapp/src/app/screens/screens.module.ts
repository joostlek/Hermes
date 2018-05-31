import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScreensRoutingModule } from './screens-routing.module';
import { ScreensComponent } from './screens.component';
import { ScreenTableComponent } from './screen-table/screen-table.component';
import {
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatButtonModule,
  MatIconModule,
  MatInputModule, MatFormFieldModule, MatDialogModule, MatSlideToggleModule
} from '@angular/material';
import { ScreenComponent } from './screen/screen.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatSlideToggleModule,
    ScreensRoutingModule,
  ],
  declarations: [ScreensComponent, ScreenTableComponent, ScreenComponent]
})
export class ScreensModule { }
