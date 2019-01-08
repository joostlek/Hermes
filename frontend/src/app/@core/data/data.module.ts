import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ImageService} from './image.service';
import {LocationService} from './location.service';
import {UserService} from './user.service';
import {SelectorService} from './selector.service';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
    ],
    providers: [
        LocationService,
        UserService,
        ImageService,
        SelectorService,
    ],
})
export class DataModule {
}
