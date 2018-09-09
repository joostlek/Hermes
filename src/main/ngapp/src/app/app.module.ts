/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { APP_BASE_HREF } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './@core/core.module';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ThemeModule } from './@theme/theme.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NbAuthJWTToken, NbAuthModule, NbPasswordAuthStrategy} from '@nebular/auth';



const formSetting: any = {
  redirectDelay: 0,
  strategy: 'email',
  alwaysFail: true,
  showMessages: {
    success: false,
  },
};

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,

    NgbModule.forRoot(),
    ThemeModule.forRoot(),
    CoreModule.forRoot(),


    NbAuthModule.forRoot({
        strategies: [
          NbPasswordAuthStrategy.setup({
            name: 'email',
            token: {
              class: NbAuthJWTToken,
              key: 'token',
            },
            baseEndpoint: 'http://localhost:4200/api/v2/auth/',
            login: {
              endpoint: 'login',
              method: 'post',
            },
            register: {
              endpoint: 'user',
            },
            logout: {
              endpoint: 'logout',
              method: 'post',
            },
          }),
        ],
        forms: {
          // login: formSetting,
          // register: formSetting,
          // requestPassword: formSetting,
          // resetPassword: formSetting,
          logout: {
            redirectDelay: 0,
          },
          validation: {
            password: {
              required: true,
              minLength: 0,
            },
          },
        },
      },
    ),
  ],
  bootstrap: [AppComponent],
  providers: [
    { provide: APP_BASE_HREF, useValue: '/' },
  ],
})
export class AppModule {
}
