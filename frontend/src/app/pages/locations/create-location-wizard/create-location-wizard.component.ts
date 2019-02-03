import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ClrWizard, ClrWizardPage} from '@clr/angular';
import {Location} from '../../../@core/data/domain/location';
import {LocationService} from '../../../@core/data/location.service';

@Component({
    selector: 'app-create-location-wizard',
    templateUrl: './create-location-wizard.component.html',
    styleUrls: ['./create-location-wizard.component.css'],
})
export class CreateLocationWizardComponent implements OnInit {
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;
    @Input('open') open = false;
    loadingFlag = false;
    errorFlag = false;
    error: any;

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

    constructor(private locationService: LocationService,
                private router: Router) {
    }

    ngOnInit() {
    }

    createLocation(): void {
        this.errorFlag = false;
        this.loadingFlag = true;
        if (!this.basicInfoPage.invalid && !this.locationInfoPage.invalid) {
            const loco = new Location();
            loco.name = this.basicInfoPage.value['name'];
            loco.street = this.locationInfoPage.value['street'];
            loco.houseNumber = this.locationInfoPage.value['houseNumber'];
            loco.zipCode = this.locationInfoPage.value['zipCode'];
            loco.city = this.locationInfoPage.value['city'];
            loco.country = this.locationInfoPage.value['country'];
            this.locationService.addLocation(loco)
                .subscribe((location) => {
                        console.log(location);
                        this.doFinish();
                        this.navigate(location);
                    },
                    (err) => {
                        this.loadingFlag = false;
                        this.errorFlag = true;
                        this.error = err;
                        throw err;
                    });
        }
    }

    navigate(location: Location): void {
        this.router.navigate(['/locations/' + location.id]);
    }

    reset(): void {
        this.wizard.reset();
        this.basicInfoPage.reset();
        this.wizard.close();
        this.loadingFlag = false;
        this.errorFlag = false;
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
}
