import {Component, Input, OnInit} from '@angular/core';
import {Screen} from '../../../@core/data/domain/screen';

@Component({
    selector: 'app-screen-table',
    templateUrl: './screen-table.component.html',
    styleUrls: ['./screen-table.component.css']
})
export class ScreenTableComponent implements OnInit {
    @Input('screens') screens: Screen[];

    constructor() {
    }

    ngOnInit() {
    }

}
