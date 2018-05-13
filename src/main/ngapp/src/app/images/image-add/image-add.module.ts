import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ImageAddRoutingModule } from './image-add-routing.module';
import { ImageAddComponent } from './image-add.component';
import {MatButtonModule, MatFormFieldModule, MatInputModule, MatStepperModule} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ImageStepperComponent } from './image-stepper/image-stepper.component';

@NgModule({
  imports: [
    CommonModule,
    MatStepperModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    ImageAddRoutingModule
  ],
  declarations: [ImageAddComponent, ImageStepperComponent]
})
export class ImageAddModule { }
