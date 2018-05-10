import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsComponent } from './promotions.component';

@NgModule({
  imports: [
    CommonModule,
    PromotionsRoutingModule
  ],
  declarations: [PromotionsComponent]
})
export class PromotionsModule { }
