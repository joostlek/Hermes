import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionAddRoutingModule } from './promotion-add-routing.module';
import { PromotionAddComponent } from './promotion-add.component';
import {
  MatButtonModule, MatDatepickerModule,
  MatFormFieldModule, MatIconModule,
  MatInputModule, MatNativeDateModule,
  MatOptionModule,
  MatSelectModule, MatSlideToggleModule,
  MatStepperModule, NativeDateModule
} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatOptionModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatIconModule,
    PromotionAddRoutingModule
  ],
  declarations: [PromotionAddComponent]
})
export class PromotionAddModule { }
