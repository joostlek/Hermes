import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {ImageService} from './image.service';
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
        ImageService,
    ],
})
export class DataModule {
}
