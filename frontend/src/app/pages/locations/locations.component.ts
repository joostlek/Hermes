import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ClrWizard, ClrWizardPage} from '@clr/angular';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';

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
    error: any;
    dataGridError: any;
    dataGridErrorFlag = false;

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
            .subscribe((locations) => this.locations = locations,
                (err) => {
                    this.dataGridError = err;
                    this.dataGridErrorFlag = true;
                });
    }

    createLocation(): void {
        this.errorFlag = false;
        this.loadingFlag = true;
        if (!this.firstPage.invalid) {
            this.locationService.addLocation(this.firstPage.value['name'])
                .subscribe((location) => {
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
        this.firstPage.reset();
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
