import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-pages',
    templateUrl: './pages.component.html',
    styleUrls: ['./pages.component.css'],
    host: {
        '[class.content-container]': 'true',
    },
})
export class PagesComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
