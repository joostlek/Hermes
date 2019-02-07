import {Component, OnInit} from '@angular/core';
import {Image} from '../../../../@core/data/domain/image';
import {ChosenLocationService} from '../../chosen-location.service';
import {ImageService} from '../../../../@core/data/image.service';

@Component({
    selector: 'app-promote-images',
    templateUrl: './promote-images.component.html',
    styleUrls: ['./promote-images.component.css']
})
export class PromoteImagesComponent implements OnInit {
    images: Image[];

    constructor(
        private chosenLocationService: ChosenLocationService,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getImages();
    }

    getImages(): void {
        this.imageService.getImagesByLocationId(this.chosenLocationService.location.id)
            .subscribe((images) => {
                    this.images = images;
                },
            );
    }
}
