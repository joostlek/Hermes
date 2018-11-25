import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {LocationService} from './location.service';
import {UserService} from './user.service';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        HttpClientModule,
    ],
    providers: [
        LocationService,
        UserService,
    ],
})
export class DataModule {
}
