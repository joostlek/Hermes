import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthComponent } from './auth.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LogoutComponent } from './logout/logout.component';
import {MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule} from "@angular/material";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    AuthRoutingModule
  ],
  declarations: [AuthComponent, LoginComponent, LogoutComponent]
})
export class AuthModule { }
