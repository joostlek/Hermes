import {Component, OnInit} from '@angular/core';
import {Image} from '../../../../@core/data/domain/image';
import {ImageService} from '../../../../@core/data/image.service';
import {ChosenLocationService} from '../../chosen-location.service';

@Component({
    selector: 'app-manage-images',
    templateUrl: './manage-images.component.html',
    styleUrls: ['./manage-images.component.css'],
})
export class ManageImagesComponent implements OnInit {
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
