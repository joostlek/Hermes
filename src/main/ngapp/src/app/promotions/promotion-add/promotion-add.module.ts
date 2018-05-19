import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionAddRoutingModule } from './promotion-add-routing.module';
import { PromotionAddComponent } from './promotion-add.component';

@NgModule({
  imports: [
    CommonModule,
    PromotionAddRoutingModule
  ],
  declarations: [PromotionAddComponent]
})
export class PromotionAddModule { }
