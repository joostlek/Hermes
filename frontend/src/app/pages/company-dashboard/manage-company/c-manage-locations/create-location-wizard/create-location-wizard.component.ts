import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState, ClrWizard, ClrWizardPage} from '@clr/angular';
import {Subject} from 'rxjs';
import {Company} from '../../../../../@core/data/domain/company';
import {Location} from '../../../../../@core/data/domain/location';
import {LocationService} from '../../../../../@core/data/location.service';
import {ChosenCompanyService} from '../../../chosen-company.service';

@Component({
    selector: 'app-create-location-wizard',
    templateUrl: './create-location-wizard.component.html',
    styleUrls: ['./create-location-wizard.component.css'],
})
export class CreateLocationWizardComponent implements OnInit {
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;

    @Input('open') openStream: Subject<boolean>;
    @Input('refreshLocationList') refreshList: Subject<boolean>;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;
    company: Company;
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
        private locationService: LocationService,
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.checkOpen();
        this.getCompany();
    }

    private checkOpen(): void {
        this.openStream.subscribe(
            (value) => this.open = value,
        );
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe((company) => {
                    this.company = company;
                },
            );
    }

    private reset(): void {
        this.wizard.reset();
        this.basicInfoPage.reset();
        this.locationInfoPage.reset();
        this.error = undefined;
    }

    private closeWizard(): void {
        this.reset();
        this.openStream.next(false);
    }

    private doCancel(): void {
        this.reset();
        this.wizard.close();
    }

    private goBack(): void {
        this.wizard.previous();
    }

    private createLocation(): void {
        if (!this.basicInfoPage.invalid && !this.locationInfoPage.invalid) {
            this.submitButtonState = ClrLoadingState.LOADING;
            const loco = new Location();
            loco.name = this.basicInfoPage.value['name'];
            loco.street = this.locationInfoPage.value['street'];
            loco.houseNumber = this.locationInfoPage.value['houseNumber'];
            loco.zipCode = this.locationInfoPage.value['zipCode'];
            loco.city = this.locationInfoPage.value['city'];
            loco.country = this.locationInfoPage.value['country'];
            this.locationService.addLocation(loco, this.company.id)
                .subscribe(
                    () => {
                        this.refreshList.next(true);
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        this.closeWizard();
                    },
                    (error) => {
                        this.submitButtonState = ClrLoadingState.ERROR;
                        this.error = JSON.parse(error.error)['message'];
                        console.error(this.error);
                    });
        }
    }

}
