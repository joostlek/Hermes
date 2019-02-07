import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Image} from '../../../../../@core/data/domain/image';
import {ImageService} from '../../../../../@core/data/image.service';

@Component({
    selector: 'app-image-detail',
    templateUrl: './image-detail.component.html',
    styleUrls: ['./image-detail.component.css'],
})
export class ImageDetailComponent implements OnInit {
    image: Image;

    constructor(
        private route: ActivatedRoute,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getParameter();
    }

    getParameter(): void {
        this.route.params
            .subscribe(
                (params) => {
                    this.getImage(+params['imageId']);
                },
            );
    }

    getImage(id: number): void {
        this.imageService.getImage(id)
            .subscribe((image) => {
                    this.image = image;
                },
            );
    }

}
