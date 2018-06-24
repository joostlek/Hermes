import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {RoleGuardService as RoleGuard} from "@app/_services/role-guard.service";
import {AuthGuardService as AuthGuard} from "@app/_services/auth-guard.service";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatGridListModule,
  MatCardModule,
  MatMenuModule,
  MatIconModule,
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatToolbarModule,
  MatSidenavModule,
  MatListModule,
  MatDialogModule, MatSliderModule
} from '@angular/material';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import {HttpClientModule} from "@angular/common/http";
import { DeleteAlertComponent } from './_dialogs/delete-alert/delete-alert.component';
import {JwtHelperService} from "@auth0/angular-jwt";

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    DeleteAlertComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    LayoutModule,
    MatDialogModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatSliderModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [
    AuthGuard,
    RoleGuard,
    JwtHelperService,
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    DeleteAlertComponent,
  ]
})
export class AppModule { }
