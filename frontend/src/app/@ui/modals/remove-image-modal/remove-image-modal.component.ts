import {Component, Input, OnInit} from '@angular/core';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {Image} from '../../../@core/data/domain/image';
import {ImageService} from '../../../@core/data/image.service';

@Component({
    selector: 'app-remove-image-modal',
    templateUrl: './remove-image-modal.component.html',
    styleUrls: ['./remove-image-modal.component.css'],
})
export class RemoveImageModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshList: Subject<boolean>;
    @Input() image: Image;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;
    error: string;

    constructor(
        private imageService: ImageService,
    ) {
    }

    ngOnInit() {
        this.checkOpenStream();
    }

    private checkOpenStream(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
            },
        );
    }

    public closeModal(): void {
        this.error = undefined;
        this.openStream.next(false);
    }

    public removeLocation(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.imageService.deleteImage(this.image.id)
            .subscribe(
                () => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
                    if (this.refreshList) {
                        this.refreshList.next(true);
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
