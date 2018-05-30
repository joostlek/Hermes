import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocationAddRoutingModule } from './location-add-routing.module';
import { LocationAddComponent } from './location-add.component';
import { LocationStepperComponent } from './location-stepper/location-stepper.component';
import {ReactiveFormsModule} from "@angular/forms";
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatOptionModule,
  MatSelectModule, MatStepperModule
} from "@angular/material";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatFormFieldModule,
    MatInputModule,
    MatStepperModule,
    LocationAddRoutingModule
  ],
  declarations: [LocationAddComponent, LocationStepperComponent]
})
export class LocationAddModule { }
