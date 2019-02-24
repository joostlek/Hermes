import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subject} from 'rxjs';
import {Image} from '../../../../../@core/data/domain/image';
import {ImageService} from '../../../../../@core/data/image.service';

@Component({
    selector: 'app-image-detail',
    templateUrl: './image-detail.component.html',
    styleUrls: ['./image-detail.component.css'],
})
export class ImageDetailComponent implements OnInit {
    image: Image;

    editImageModal: Subject<boolean> = new Subject<boolean>();
    deleteImageModal: Subject<boolean> = new Subject<boolean>();
    toListStream: Subject<boolean> = new Subject<boolean>();

    constructor(
        private route: ActivatedRoute,
        private imageService: ImageService,
        private location: Location,
    ) {
    }

    ngOnInit() {
        this.getParameter();
        this.watchList();
    }

    private getParameter(): void {
        this.route.params
            .subscribe(
                (params) => {
                    this.getImage(+params['imageId']);
                },
            );
    }

    private getImage(id: number): void {
        this.imageService.getImage(id)
            .subscribe((image) => {
                    this.image = image;
                },
            );
    }

    private watchList(): void {
        this.toListStream.subscribe(
            () => {
                this.location.back();
            },
        );
    }

    public openEditModal(): void {
        this.editImageModal.next(true);
    }

    public openDeleteModal(): void {
        this.deleteImageModal.next(true);
    }

}
