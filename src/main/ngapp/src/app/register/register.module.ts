import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register.component';
import { RegisterStepperComponent } from './register-stepper/register-stepper.component';
import {MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatStepperModule} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatStepperModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    RegisterRoutingModule,
  ],
  declarations: [RegisterComponent, RegisterStepperComponent]
})
export class RegisterModule { }
