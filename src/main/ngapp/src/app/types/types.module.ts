import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypesRoutingModule } from './types-routing.module';
import { TypesComponent } from './types.component';
import { TypeTableComponent } from './type-table/type-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    TypesRoutingModule,
  ],
  declarations: [TypesComponent, TypeTableComponent]
})
export class TypesModule { }
