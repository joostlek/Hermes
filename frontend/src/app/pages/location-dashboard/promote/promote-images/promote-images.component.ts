import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {Image} from '../../../../@core/data/domain/image';
import {ImageService} from '../../../../@core/data/image.service';
import {ChosenLocationService} from '../../chosen-location.service';

@Component({
    selector: 'app-promote-images',
    templateUrl: './promote-images.component.html',
    styleUrls: ['./promote-images.component.css'],
})
export class PromoteImagesComponent implements OnInit {
    images: Image[];

    constructor(
        private chosenLocationService: ChosenLocationService,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getCurrentLocation()
            .subscribe((location) => {
                    this.getImages(location.id);
                },
            );
    }

    getCurrentLocation() {
        return this.chosenLocationService.getLocation()
            .pipe(
                filter((value) => value !== null),
            );
    }

    getImages(locationId: number): void {
        this.imageService.getImagesByLocationId(locationId)
            .subscribe((images) => {
                    this.images = images;
                },
            );
    }
}
