import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ClarityModule} from '@clr/angular';
import {DataModule} from './@core/data/data.module';
import {RepeatPasswordValidatorDirective} from './@core/directives/repeat-password.directive';
import {ErrorInterceptor} from './@core/interceptor/error-interceptor';
import {JsonInterceptor} from './@core/interceptor/json-interceptor';
import {SafePipe} from './@core/pipes/safe.pipe';
import {UiModule} from './@ui/ui.module';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

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
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true,
        },
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
