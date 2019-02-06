import {Component, Input, OnInit} from '@angular/core';
import {Location} from '../../../@core/data/domain/location';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-location-list-item',
    templateUrl: './location-list-item.component.html',
    styleUrls: ['./location-list-item.component.css'],
})
export class LocationListItemComponent implements OnInit {
    @Input('location') location: Location;
    @Input('company') company: Company;

    constructor() {
    }

    ngOnInit() {
    }

}
