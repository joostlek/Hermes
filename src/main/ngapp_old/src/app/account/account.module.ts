import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { AccountComponent } from './account.component';
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
    AccountRoutingModule
  ],
  declarations: [AccountComponent]
})
export class AccountModule { }
