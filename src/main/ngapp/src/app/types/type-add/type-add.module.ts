import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypeAddRoutingModule } from './type-add-routing.module';
import { TypeAddComponent } from './type-add.component';
import { TypeStepperComponent } from './type-stepper/type-stepper.component';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatOptionModule, MatSelectModule,
  MatSlideToggleModule,
  MatStepperModule
} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatIconModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSlideToggleModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    TypeAddRoutingModule
  ],
  declarations: [TypeAddComponent, TypeStepperComponent]
})
export class TypeAddModule { }
