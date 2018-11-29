import {Component, Input, OnInit} from '@angular/core';
import {Location} from '../../../@core/data/domain/location';

@Component({
    selector: 'app-location-table',
    templateUrl: './location-table.component.html',
    styleUrls: ['./location-table.component.css']
})
export class LocationTableComponent implements OnInit {
    @Input('locations') locations: Location[];

    constructor() {
    }

    ngOnInit() {
    }

}
