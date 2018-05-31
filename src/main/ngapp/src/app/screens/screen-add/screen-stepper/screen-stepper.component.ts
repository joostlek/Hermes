import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ScreenService } from "@app/_services/screen.service";
import { LocationService } from "@app/_services/location.service";
import { Location } from '@app/_models/location';

@Component({
  selector: 'app-screen-stepper',
  templateUrl: './screen-stepper.component.html',
  styleUrls: ['./screen-stepper.component.scss']
})
export class ScreenStepperComponent implements OnInit {
  formGroup: FormGroup;
  locations: Location[] = [];

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(
    private _FormBuilder: FormBuilder,
    private screenService: ScreenService,
    private locationService: LocationService,
  ) {  }

  ngOnInit() {
    this.formGroup = this._FormBuilder.group({
      formArray: this._FormBuilder.array([
        this._FormBuilder.group({
          screenName: ['', Validators.required],
          screenHeight: ['', Validators.required],
          screenWidth: ['', Validators.required],
          location: ['', Validators.required],
          thirdParty: ['']
        })
      ])
    });
    this.getLocations();
  }

  getLocations() {
    this.locationService.getLocations()
      .subscribe(locations => this.locations = locations)
  }

  finishSetUp() {
    this.screenService.addScreen(
      this.formArray.get([0]).value['screenName'],
      this.formArray.get([0]).value['screenHeight'],
      this.formArray.get([0]).value['screenWidth'],
      this.formArray.get([0]).value['location'],
      this.formArray.get([0]).value['thirdParty']
    )
  }
}
