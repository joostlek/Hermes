import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScreenAddRoutingModule } from './screen-add-routing.module';
import { ScreenAddComponent } from './screen-add.component';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatOptionModule,
  MatSelectModule, MatSlideToggleModule, MatStepperModule
} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    MatStepperModule,
    MatSlideToggleModule,
    ScreenAddRoutingModule
  ],
  declarations: [ScreenAddComponent]
})
export class ScreenAddModule { }
