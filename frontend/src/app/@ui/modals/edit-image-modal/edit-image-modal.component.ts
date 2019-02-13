import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {Image} from '../../../@core/data/domain/image';
import {ImageService} from '../../../@core/data/image.service';

@Component({
    selector: 'app-edit-image-modal',
    templateUrl: './edit-image-modal.component.html',
    styleUrls: ['./edit-image-modal.component.css'],
})
export class EditImageModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshStream: Subject<boolean>;
    @Input() image: Image;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;

    error: string;

    basicInfoPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
    });

    constructor(
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.checkOpenStream();
        this.fillForm(this.image);
    }

    private checkOpenStream(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
            },
        );
    }

    private fillForm(image: Image): void {
        this.basicInfoPage.controls['name'].setValue(image.name);
    }

    public closeModal(): void {
        this.basicInfoPage.reset();
        this.openStream.next(false);
    }

    public getErrorCount(formGroup: FormGroup): number {
        let res = 0;
        for (const key in formGroup.controls) {
            if (formGroup.controls.hasOwnProperty(key)) {
                const value = formGroup.controls[key];
                if (value.errors !== null) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public updateImage(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        if (!this.basicInfoPage.invalid) {
            this.image.name = this.basicInfoPage.value['name'];
            this.imageService.updateImage(this.image)
                .subscribe(
                    () => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        if (this.refreshStream) {
                            this.refreshStream.next(true);
                        }
                        this.closeModal();
                    },
                    (error) => {
                        this.error = JSON.parse(error.error)['message'];
                        this.submitButtonState = ClrLoadingState.ERROR;
                        console.error(this.error);
                    },
                );
        }
    }

    public isDisabled(): boolean {
        return this.basicInfoPage.invalid;
    }

}
