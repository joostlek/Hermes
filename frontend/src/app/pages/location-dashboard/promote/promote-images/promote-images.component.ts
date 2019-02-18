import {Component, OnInit} from '@angular/core';
import {Image} from '../../../../@core/data/domain/image';
import {Location} from '../../../../@core/data/domain/location';
import {ImageService} from '../../../../@core/data/image.service';
import {ChosenLocationService} from '../../chosen-location.service';
import {Subject} from 'rxjs';

@Component({
    selector: 'app-promote-images',
    templateUrl: './promote-images.component.html',
    styleUrls: ['./promote-images.component.css'],
})
export class PromoteImagesComponent implements OnInit {
    imageWizard: Subject<boolean> = new Subject<boolean>();

    images: Image[];
    location: Location;

    constructor(
        private chosenLocationService: ChosenLocationService,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getCurrentLocation()
            .subscribe((location) => {
                    this.getImages(location.id);
                this.location = location;
                },
            );
    }

    private openImageWizard(): void {
        this.imageWizard.next(true);
    }

    getCurrentLocation() {
        return this.chosenLocationService.getLocation();
    }

    getImages(locationId: number): void {
        this.imageService.getImagesByLocationId(locationId)
            .subscribe((images) => {
                    this.images = images;
                },
            );
    }
}
