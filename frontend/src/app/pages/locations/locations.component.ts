import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrWizard, ClrWizardPage} from '@clr/angular';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-locations',
    templateUrl: './locations.component.html',
    styleUrls: ['./locations.component.css'],
})
export class LocationsComponent implements OnInit {
    @ViewChild('wizardlg') wizard: ClrWizard;
    @ViewChild('finalPage') finalPage: ClrWizardPage;
    wizardOpen = false;
    locations: Location[] = [];
    name: string;
    loadingFlag = false;
    errorFlag = false;

    firstPage = new FormGroup({
        name: new FormControl('', [Validators.required]),
    });

    constructor(
        private locationService: LocationService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.getLocations();
    }

    getLocations(): void {
        this.locationService.getAllLocations()
            .subscribe((locations) => this.locations = locations);
    }

    createLocation(): void {
        if (!this.firstPage.invalid) {
            this.locationService.addLocation(this.firstPage.value['name'])
                .subscribe((location) => {
                    this.doFinish();
                    this.navigate(location);
                });
        }
    }

    navigate(location: Location): void {
        this.router.navigate(['/locations/' + location.id]);
    }

    reset(): void {
        this.wizard.reset();
    }

    doCancel(): void {
        this.wizard.close();
    }

    goBack(): void {
        this.wizard.previous();
    }

    doFinish(): void {
        this.wizard.forceFinish();
        this.wizard.close();
        this.firstPage.reset();
    }

}
