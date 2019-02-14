import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';

@Component({
    selector: 'app-location-list',
    templateUrl: './location-list.component.html',
    styleUrls: ['./location-list.component.css'],
})
export class LocationListComponent implements OnInit {
    @Input() locationStream: Observable<Location[]>;
    @Input() lazyLoadStream: Observable<boolean>;

    locations: Location[];

    constructor() {
    }

    ngOnInit() {
        this.getLocations();
    }

    private getLocations(): void {
        this.locationStream.subscribe(
            (locations: Location[]) => {
                this.locations = locations;
            },
        );
    }

}
