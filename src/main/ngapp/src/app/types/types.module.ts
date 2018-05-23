import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypesRoutingModule } from './types-routing.module';
import { TypesComponent } from './types.component';
import { TypeTableComponent } from './type-table/type-table.component';
import {
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatButtonModule,
  MatIconModule, MatInputModule, MatFormFieldModule, MatOptionModule, MatSelectModule, MatSlideToggleModule
} from '@angular/material';
import { TypeComponent } from './type/type.component';
import { ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatSlideToggleModule,
    TypesRoutingModule,
  ],
  declarations: [TypesComponent, TypeTableComponent, TypeComponent]
})
export class TypesModule { }
