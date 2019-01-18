import {Component, Input, OnInit} from '@angular/core';
import {Location} from '../../../@core/data/domain/location';
import {SelectorService} from '../../../@core/data/selector.service';

@Component({
    selector: 'app-location-table',
    templateUrl: './location-table.component.html',
    styleUrls: ['./location-table.component.css'],
})
export class LocationTableComponent implements OnInit {
    @Input('locations') locations: Location[];
    @Input('error') error: boolean;
    @Input('select') select: SelectorService;

    constructor() {
    }

    ngOnInit() {
    }

    onSelect(location: Location) {
        this.select.updateSelectedLocation(location);
    }

}
