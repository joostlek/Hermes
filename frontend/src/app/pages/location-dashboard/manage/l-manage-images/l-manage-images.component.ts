import {Component, OnInit} from '@angular/core';
import {Image} from '../../../../@core/data/domain/image';
import {Location} from '../../../../@core/data/domain/location';
import {ImageService} from '../../../../@core/data/image.service';
import {ChosenLocationService} from '../../chosen-location.service';

@Component({
    selector: 'app-l-manage-images',
    templateUrl: './l-manage-images.component.html',
    styleUrls: ['./l-manage-images.component.css'],
})
export class LManageImagesComponent implements OnInit {
    images: Image[];

    constructor(
        private chosenLocationService: ChosenLocationService,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getLocation();
    }

    private getLocation(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location: Location) => {
                    this.getImages(location.id);
                },
            );
    }

    private getImages(locationId: number): void {
        this.imageService.getImagesByLocationId(locationId)
            .subscribe(
                (images) => {
                    this.images = images;
                },
            );
    }
}
