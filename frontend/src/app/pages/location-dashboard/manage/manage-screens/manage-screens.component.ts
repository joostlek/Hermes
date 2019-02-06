import {Component, OnInit} from '@angular/core';
import {Screen} from '../../../../@core/data/domain/screen';
import {ScreenService} from '../../../../@core/data/screen.service';
import {ChosenLocationService} from '../../chosen-location.service';

@Component({
    selector: 'app-manage-screens',
    templateUrl: './manage-screens.component.html',
    styleUrls: ['./manage-screens.component.css'],
})
export class ManageScreensComponent implements OnInit {
    screens: Screen[];

    constructor(
        private chosenLocationService: ChosenLocationService,
        private screenService: ScreenService,
    ) {
    }

    ngOnInit() {
        this.getScreens();
    }

    getScreens(): void {
        this.screenService.getScreensByLocationId(this.chosenLocationService.location.id)
            .subscribe((value) => {
                    this.screens = value;
                },
            );
    }
}
