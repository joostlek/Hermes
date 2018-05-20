import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsComponent } from './promotions.component';
import { PromotionTableComponent } from './promotion-table/promotion-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule} from '@angular/material';
import { PromotionDetailComponent } from './promotion-detail/promotion-detail.component';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    PromotionsRoutingModule,
  ],
  declarations: [PromotionsComponent, PromotionTableComponent, PromotionDetailComponent]
})
export class PromotionsModule { }
