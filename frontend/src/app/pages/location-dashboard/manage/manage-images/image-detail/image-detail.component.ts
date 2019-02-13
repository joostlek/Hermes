import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ImageService} from '../../../../../@core/data/image.service';
import {Image} from '../../../../../@core/data/domain/image';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
    selector: 'app-image-detail',
    templateUrl: './image-detail.component.html',
    styleUrls: ['./image-detail.component.css']
})
export class ImageDetailComponent implements OnInit {
    image: Image;
    style: any;

    constructor(
        private route: ActivatedRoute,
        private imageService: ImageService,
        private dom: DomSanitizer,
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
                this.style = {};
                this.style['width'] = image.screen.width + 'px';
                this.style['height'] = image.screen.height + 'px';
                this.style = this.dom.bypassSecurityTrustStyle(this.style);
                },
            );
    }

}
