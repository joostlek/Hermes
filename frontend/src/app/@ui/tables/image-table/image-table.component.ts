import {Component, Input, OnInit} from '@angular/core';
import {Image} from '../../../@core/data/domain/image';

@Component({
    selector: 'app-image-table',
    templateUrl: './image-table.component.html',
    styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
    @Input('images') images: Image[];
    @Input('error') error: boolean;

    constructor() {
    }

    ngOnInit() {
    }

}
