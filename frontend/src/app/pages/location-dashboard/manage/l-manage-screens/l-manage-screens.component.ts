import {Component, OnInit} from '@angular/core';
import {Screen} from '../../../../@core/data/domain/screen';
import {ChosenLocationService} from '../../chosen-location.service';
import {ScreenService} from '../../../../@core/data/screen.service';
import {Observable} from 'rxjs';
import {Location} from '../../../../@core/data/domain/location';

@Component({
    selector: 'app-l-manage-screens',
    templateUrl: './l-manage-screens.component.html',
    styleUrls: ['./l-manage-screens.component.css']
})
export class LManageScreensComponent implements OnInit {
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
