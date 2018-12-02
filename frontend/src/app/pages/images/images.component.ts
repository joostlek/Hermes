import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {Image} from '../../@core/data/domain/image';
import {Location} from '../../@core/data/domain/location';
import {ImageService} from '../../@core/data/image.service';
import {SelectorService} from '../../@core/data/selector.service';

@Component({
    selector: 'app-images',
    templateUrl: './images.component.html',
    styleUrls: ['./images.component.css'],
})
export class ImagesComponent implements OnInit, OnDestroy {
    constructor(
        private imageService: ImageService,
        private selectorService: SelectorService,
    ) {
    }

    dataGridErrorFlag = false;
    private ngUnsubscribe = new Subject();
    private ngTempUnsubscribe = new Subject();
    images: Image[] = [];
    wizardOpen = false;

    ngOnInit() {
        this.getLocation();
    }

    getLocation(): void {
        this.selectorService.selectedLocation
            .pipe(
                takeUntil(this.ngUnsubscribe),
            )
            .subscribe((location) => {
                this.ngTempUnsubscribe.next();
                this.images = [];
                this.dataGridErrorFlag = false;
                this.getImages(location);
            }, (err) => {
                this.dataGridErrorFlag = true;
                throw err;
            });
    }

    getImages(location: Location): void {
        this.imageService.getImagesByLocationIdByUserId(location.id, 1)
            .pipe(
                takeUntil(this.ngTempUnsubscribe),
            )
            .subscribe((images) => this.images = images,
                (err) => {
                    this.dataGridErrorFlag = true;
                    throw err;
                });
    }

    ngOnDestroy(): void {
        this.ngUnsubscribe.next();
        this.ngUnsubscribe.complete();
    }
}
