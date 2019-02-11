import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Location} from '../../../../@core/data/domain/location';
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
        this.getLocation()
            .subscribe((location) => {
                    this.getScreens(location.id);
                },
            );
    }

    getScreens(locationId: number): void {
        this.screenService.getScreensByLocationId(locationId)
            .subscribe((screens) => this.screens = screens);
    }

    getLocation(): Observable<Location> {
        return this.chosenLocationService.getLocation();
    }
}
