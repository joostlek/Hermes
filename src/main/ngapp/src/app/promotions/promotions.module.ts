import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsComponent } from './promotions.component';
import { PromotionTableComponent } from './promotion-table/promotion-table.component';
import {
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatButtonModule,
  MatIconModule,
  MatInputModule, MatDialogModule, MatFormFieldModule
} from '@angular/material';
import { PromotionComponent } from './promotion/promotion.component';
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
    MatDialogModule,
    MatFormFieldModule,
    PromotionsRoutingModule,
  ],
  declarations: [PromotionsComponent, PromotionTableComponent, PromotionComponent]
})
export class PromotionsModule { }
