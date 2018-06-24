import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSelectModule,
  MatSliderModule,
  MatStepperModule
} from "@angular/material";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { ImageAddRoutingModule } from './image-add-routing.module';
import { ImageAddComponent } from './image-add.component';

@NgModule({
  imports: [
    CommonModule,
    MatStepperModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    MatSliderModule,
    MatIconModule,
    ImageAddRoutingModule
  ],
  declarations: [
    ImageAddComponent,
  ]
})
export class ImageAddModule { }
