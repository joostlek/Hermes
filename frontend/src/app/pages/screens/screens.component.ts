import {Component, OnInit} from '@angular/core';
import {ScreenService} from '../../@core/data/screen.service';
import {Screen} from '../../@core/data/domain/screen';

@Component({
    selector: 'app-screens',
    templateUrl: './screens.component.html',
    styleUrls: ['./screens.component.css']
})
export class ScreensComponent implements OnInit {
    screens: Screen[] = [];

    constructor(
        private screenService: ScreenService,
    ) {
    }

    ngOnInit() {
        this.getScreens();
    }

    getScreens(): void {
        this.screenService.getScreens()
            .subscribe((screens) => {
                this.screens = screens;
            });
    }

}
