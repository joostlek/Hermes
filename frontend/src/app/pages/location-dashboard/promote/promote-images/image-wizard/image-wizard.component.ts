import {Component, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState, ClrWizard, ClrWizardPage} from '@clr/angular';
import {Observable, Subject, Subscription} from 'rxjs';
import {filter, takeUntil} from 'rxjs/operators';
import {Company} from '../../../../../@core/data/domain/company';
import {Image} from '../../../../../@core/data/domain/image';
import {Location} from '../../../../../@core/data/domain/location';
import {Screen} from '../../../../../@core/data/domain/screen';
import {FileUploadService} from '../../../../../@core/data/file-upload.service';
import {ImageService} from '../../../../../@core/data/image.service';
import {ScreenService} from '../../../../../@core/data/screen.service';
import {SelectorService} from '../../../../../@core/data/selector.service';
import {ChosenLocationService} from '../../../chosen-location.service';

@Component({
    selector: 'app-image-wizard',
    templateUrl: './image-wizard.component.html',
    styleUrls: ['./image-wizard.component.css'],
})
export class ImageWizardComponent implements OnInit, OnDestroy {
    @Input('open') open: boolean;
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;

    constructor(
        private imageService: ImageService,
        private selectorService: SelectorService,
        private fileUploadService: FileUploadService,
        private screenService: ScreenService,
        private chosenLocationService: ChosenLocationService,
    ) {
    }

    wizardOpen = false;
    loadingFlag = false;
    errorFlag = false;
    error: any;
    company: Company;
    screens: Screen[] = [];
    fileUploadButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    fileUploadSubscription: Subscription;
    fileUploadSubject: Subject<boolean> = new Subject();

    subject: Subject<boolean> = new Subject();

    fileToUpload: File = null;

    firstPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
        screenId: new FormControl(''),
    });

    secondPage = new FormGroup({
        url: new FormControl({value: '', disabled: true}, [Validators.required]),
    });

    ngOnInit() {
        this.getScreens();
        this.getCompany();
    }

    getScreens(): void {
        this.getLocation()
            .pipe(
                takeUntil(this.subject),
            )
            .subscribe((location) => {
                    this.getScreensByLocationId(location.id);
                },
            );
    }

    getLocation(): Observable<Location> {
        return this.chosenLocationService.getLocation()
            .pipe(
                filter((value) => value !== null),
            );
    }

    getCompany(): void {
        this.chosenLocationService.getCompany()
            .pipe(
                filter((value) => value !== null),
            ).subscribe(
            (company) => this.company = company,
        );
    }

    getScreensByLocationId(locationId: number): void {
        this.screenService.getScreensByLocationId(locationId)
            .subscribe(
                (screens) => {
                    this.screens = screens;
                },
            );
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

    createImage(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        if (!this.firstPage.invalid && !this.secondPage.invalid) {
            const image = new Image();
            image.name = this.firstPage.value['name'];
            image.url = this.secondPage.value['url'];
            this.imageService.addImage(image, this.firstPage.value['screenId'], this.company.id)
                .subscribe(() => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
                    this.reset();
                });
        }
    }

    reset(): void {
        this.wizard.reset();
        this.firstPage.reset();
        this.wizard.close();
        this.submitButtonState = ClrLoadingState.DEFAULT;
    }

    doCancel(): void {
        this.reset();
    }

    goBack(): void {
        this.wizard.previous();
    }

    doFinish(): void {
        this.wizard.forceFinish();
        this.reset();
    }

    ngOnDestroy(): void {
        this.subject.next(false);
    }

}
