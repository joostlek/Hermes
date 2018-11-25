import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-image-table',
    templateUrl: './image-table.component.html',
    styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
    images: object[] = [];

    constructor() {
    }

    ngOnInit() {
    }

}
