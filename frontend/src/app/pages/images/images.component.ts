import {Component, OnInit} from '@angular/core';
import {ImageService} from '../../@core/data/image.service';
import {Image} from '../../@core/data/domain/image';

@Component({
    selector: 'app-images',
    templateUrl: './images.component.html',
    styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

    constructor(
        private imageService: ImageService,
    ) {
    }

    images: Image[] = [];

    ngOnInit() {
        this.getImages();
    }

    getImages(): void {
        this.imageService.getImages()
            .subscribe((images) => this.images = images);
    }
}
