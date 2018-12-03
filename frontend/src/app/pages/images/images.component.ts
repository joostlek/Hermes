import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState, ClrWizard, ClrWizardPage} from '@clr/angular';
import {Subject} from 'rxjs';
import {filter, startWith, takeUntil} from 'rxjs/operators';
import {Image} from '../../@core/data/domain/image';
import {Location} from '../../@core/data/domain/location';
import {ImageService} from '../../@core/data/image.service';
import {SelectorService} from '../../@core/data/selector.service';
import {FileUploadService} from '../../@core/data/file-upload.service';

@Component({
    selector: 'app-images',
    templateUrl: './images.component.html',
    styleUrls: ['./images.component.css'],
})
export class ImagesComponent implements OnInit, OnDestroy {
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;

    constructor(
        private imageService: ImageService,
        private selectorService: SelectorService,
        private fileUploadService: FileUploadService,
    ) {
    }

    dataGridErrorFlag = false;
    private ngUnsubscribe = new Subject();
    private ngTempUnsubscribe = new Subject();
    images: Image[] = [];
    wizardOpen = false;
    loadingFlag = false;
    errorFlag = false;
    error: any;
    fileUploadButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    fileToUpload: File = null;

    firstPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
    });

    secondPage = new FormGroup({
        url: new FormControl({value: '', disabled: true}, [Validators.required]),
    });

    ngOnInit() {
        this.getLocation();
        this.setSubscription();
    }

    private setSubscription(): void {
        this.ngUnsubscribe.subscribe(() => this.ngTempUnsubscribe.next());
    }

    getLocation(): void {
        this.selectorService.selectedLocation
            .pipe(
                startWith(this.selectorService.location),
                filter((location) => location !== null),
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

    fileUpload(files: FileList): void {
        this.fileUploadButtonState = ClrLoadingState.LOADING;
        this.fileToUpload = files.item(0);
        if (this.fileToUpload === null) {
            this.fileUploadButtonState = ClrLoadingState.ERROR;
        } else {
            this.fileUploadService.uploadFile(this.fileToUpload)
                .subscribe((fileUrl) => {
                        this.fileUploadButtonState = ClrLoadingState.SUCCESS;
                        return this.secondPage.setValue({url: fileUrl});
                    },
                    (err) => {
                        this.fileUploadButtonState = ClrLoadingState.ERROR;
                        throw err;
                    });

        }
    }
}
