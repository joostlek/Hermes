import {Component, Input, OnInit} from '@angular/core';
import {Image} from '../../../@core/data/domain/image';

@Component({
    selector: 'app-example-screen',
    templateUrl: './example-screen.component.html',
    styleUrls: ['./example-screen.component.css']
})
export class ExampleScreenComponent implements OnInit {
    @Input() screen: Screen;
    @Input() image: Image;
    @Input() scale = 1;

    width: number;
    height: number;

    constructor() {
    }

    ngOnInit() {
        this.calculateSize();
    }

    private calculateSize(): void {
        if (this.scale === 0) {
            this.scale = 1;
        }
        this.width = this.screen.width / this.scale;
        this.height = this.screen.height / this.scale;
    }
}
