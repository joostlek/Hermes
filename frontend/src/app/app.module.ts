import {ErrorHandler, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {ClarityModule} from '@clr/angular';
import {UiModule} from './@ui/ui.module';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorsHandler} from './@core/errors/errors-handler';
import {DataModule} from './@core/data/data.module';
import {AuthInterceptor} from './@core/interceptor/auth-interceptor';
import {RepeatPasswordValidatorDirective} from './@core/directives/repeat-password.directive';

@NgModule({
    declarations: [
        AppComponent,
        RepeatPasswordValidatorDirective,
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
        ErrorsHandler,
        {
            provide: ErrorHandler,
            useClass: ErrorsHandler,
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
