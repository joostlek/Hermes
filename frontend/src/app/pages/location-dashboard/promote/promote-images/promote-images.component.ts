import {Component, OnInit} from '@angular/core';
import {Image} from '../../../../@core/data/domain/image';
import {Location} from '../../../../@core/data/domain/location';
import {ImageService} from '../../../../@core/data/image.service';
import {ChosenLocationService} from '../../chosen-location.service';
import {Subject} from 'rxjs';
import {Company} from '../../../../@core/data/domain/company';

@Component({
    selector: 'app-promote-images',
    templateUrl: './promote-images.component.html',
    styleUrls: ['./promote-images.component.css'],
})
export class PromoteImagesComponent implements OnInit {
    imageWizard: Subject<boolean> = new Subject<boolean>();

    images: Image[];
    location: Location;
    company: Company;

    constructor(
        private chosenLocationService: ChosenLocationService,
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.getLocation();
    }

    private openImageWizard(): void {
        this.imageWizard.next(true);
    }

    private getLocation(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location) => {
                    this.location = location;
                    this.getCompany();
                },
            );
    }

    private getCompany(): void {
        this.chosenLocationService.getCompany()
            .subscribe(
                (company) => {
                    this.company = company;
                    this.getImages();
                },
            );
    }

    private getImages(): void {
        this.imageService.getImagesByLocationIdByCompanyId(this.location.id, this.company.id)
            .subscribe(
                (images) => {
                    this.images = images;
                },
            );
    }
}
