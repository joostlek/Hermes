import {Component, OnInit} from '@angular/core';
import {ImageService} from '../../@core/data/image.service';
import {Image} from '../../@core/data/domain/image';
import {SelectorService} from '../../@core/data/selector.service';

@Component({
    selector: 'app-images',
    templateUrl: './images.component.html',
    styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

    constructor(
        private imageService: ImageService,
        private selectorService: SelectorService,
    ) {
    }

    images: Image[] = [];

    ngOnInit() {
        // this.getLocation();
    }

    // getLocation(): void {
    //     this.selectorService.selectedLocation
    //         .subscribe((location) => this.getImages(location));
    // }
    //
    // getImages(location: Location): void {
    //     this.imageService.getImagesByLocation(location)
    //         .subscribe((images) => this.images = images);
    // }
}
