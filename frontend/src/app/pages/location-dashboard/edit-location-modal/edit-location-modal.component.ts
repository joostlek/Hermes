import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {Location} from '../../../@core/data/domain/location';
import {LocationService} from '../../../@core/data/location.service';
import {ChosenLocationService} from '../chosen-location.service';

@Component({
    selector: 'app-edit-location-modal',
    templateUrl: './edit-location-modal.component.html',
    styleUrls: ['./edit-location-modal.component.css'],
})
export class EditLocationModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshStream: Subject<boolean>;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    location: Location;
    open = false;

    error: string;

    basicInfoPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
    });

    locationInfoPage = new FormGroup({
        street: new FormControl('', [Validators.required]),
        houseNumber: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required]),
        city: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
    });

    constructor(
        private chosenLocationService: ChosenLocationService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
                if (value === true) {
                    this.lazyLoadModal();
                }
            },
        );
    }

    private lazyLoadModal(): void {
        this.chosenLocationService.getLocation()
            .subscribe(
                (location: Location) => {
                    this.location = location;
                    this.fillForm(location);
                },
            );
    }

    private fillForm(location: Location): void {
        this.basicInfoPage.controls['name'].setValue(location.name);
        this.locationInfoPage.controls['street'].setValue(location.street);
        this.locationInfoPage.controls['houseNumber'].setValue(location.houseNumber);
        this.locationInfoPage.controls['zipCode'].setValue(location.zipCode);
        this.locationInfoPage.controls['city'].setValue(location.city);
        this.locationInfoPage.controls['country'].setValue(location.country);
    }

    public closeModal(): void {
        this.basicInfoPage.reset();
        this.locationInfoPage.reset();
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

    public updateLocation(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        if (!this.locationInfoPage.invalid && !this.basicInfoPage.invalid) {
            this.location.name = this.basicInfoPage.value['name'];
            this.location.street = this.locationInfoPage.value['street'];
            this.location.houseNumber = this.locationInfoPage.value['houseNumber'];
            this.location.zipCode = this.locationInfoPage.value['zipCode'];
            this.location.city = this.locationInfoPage.value['city'];
            this.location.country = this.locationInfoPage.value['country'];
            this.locationService.updateLocation(this.location)
                .subscribe(
                    (location: Location) => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        this.chosenLocationService.pushNewLocation(location);
                        this.refreshStream.next(true);
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
        return this.locationInfoPage.invalid || this.basicInfoPage.invalid;
    }

}
