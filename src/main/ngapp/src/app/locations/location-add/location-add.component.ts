import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocationService} from "@app/_services/location.service";
import {User} from "@app/_models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-location-add',
  templateUrl: './location-add.component.html',
  styleUrls: ['./location-add.component.scss']
})
export class LocationAddComponent implements OnInit {
  formGroup: FormGroup;
  user: User;

  constructor(
    private location: Location,
    private _FormBuilder: FormBuilder,
    private locationService: LocationService,
    private router: Router,
  ) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.fillForm();
  }

  get locationName() { return this.formGroup.get('locationName'); }

  get street() { return this.formGroup.get('street'); }

  get houseNumber() { return this.formGroup.get('houseNumber'); }

  get zipCode() { return this.formGroup.get('zipCode'); }

  get city() { return this.formGroup.get('city'); }

  get country() { return this.formGroup.get('country'); }


  goBack() {
    this.location.back();
  }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      locationName: ['', Validators.required],
      street: ['', Validators.required],
      houseNumber: ['', [Validators.required, Validators.pattern('[1-9]+[a-zA-Z]*')]],
      zipCode: ['', [Validators.required,  Validators.pattern('[1-9][0-9]{3} ?(?!sa|sd|ss|SA|SD|SS)[a-zA-Z]{2}$')]],
      city: ['', Validators.required],
      country: ['', Validators.required]
    })
  }

  finish() {
    if (this.formGroup.valid) {
      this.locationService.addLocation(
        this.locationName.value,
        this.user.id,
        this.street.value,
        this.houseNumber.value,
        this.zipCode.value,
        this.city.value,
        this.country.value
      )
        .subscribe(_ => {
          this.router.navigateByUrl('/locations')
        })
    }
  }

  getErrorMessage(field) {
    return field.hasError('required') ? 'You must enter a value' :
      field.hasError('email') ? 'You must enter a valid email' :
        field.hasError('pattern') ? 'You must enter a valid value' :
          '';
  }
}
