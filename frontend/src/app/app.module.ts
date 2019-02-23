import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {ClarityModule} from '@clr/angular';
import {UiModule} from './@ui/ui.module';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DataModule} from './@core/data/data.module';
import {RepeatPasswordValidatorDirective} from './@core/directives/repeat-password.directive';
import {RefreshTokenInterceptor} from './@core/interceptor/refresh-token-interceptor';
import {JsonInterceptor} from './@core/interceptor/json-interceptor';
import {SafePipe} from './@core/pipes/safe.pipe';

@NgModule({
    declarations: [
        AppComponent,
        RepeatPasswordValidatorDirective,
        SafePipe,
    ],
    imports: [
        HttpClientModule,
        BrowserModule,
        DataModule,
        BrowserAnimationsModule,
        ClarityModule,
        UiModule,
        AppRoutingModule,
    ],
    providers: [
        // ErrorsHandler,
        // {
        //     provide: ErrorHandler,
        //     useClass: ErrorsHandler,
        // },
        // {
        //     provide: HTTP_INTERCEPTORS,
        //     useClass: RefreshTokenInterceptor,
        //     multi: true,
        // },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: JsonInterceptor,
            multi: true,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
