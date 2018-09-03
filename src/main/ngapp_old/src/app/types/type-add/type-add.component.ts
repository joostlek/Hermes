import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Location as Loca } from '@app/_models/Location';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "@app/_models/user";
import {TypeService} from "@app/_services/type.service";
import {Router} from "@angular/router";
import {LocationService} from "@app/_services/location.service";

@Component({
  selector: 'app-type-add',
  templateUrl: './type-add.component.html',
  styleUrls: ['./type-add.component.scss']
})
export class TypeAddComponent implements OnInit {
  user: User;
  formGroup: FormGroup;
  myLocations: Loca[];

  constructor(
    private location: Location,
    private _FormBuilder: FormBuilder,
    private typeService: TypeService,
    private router: Router,
    private locationService: LocationService,
    ) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.fillForm();
    this.getMyLocations();
  }

  get typeName() { return this.formGroup.get('typeName'); }

  get active() { return this.formGroup.get('active'); }

  get locations() { return this.formGroup.get('locations'); }

  get time() { return this.formGroup.get('time'); }

  get price() { return this.formGroup.get('price'); }

  get amountOfImages() { return this.formGroup.get('amountOfImages'); }

  get exclusive() { return this.formGroup.get('exclusive'); }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      typeName: ['', Validators.required],
      active: [true],
      locations: ['', Validators.required],
      time: ['', Validators.required],
      price: ['', Validators.required],
      amountOfImages: ['', Validators.required],
      exclusive: [false]
    })
  }

  goBack() {
    this.location.back();
  }

  finish() {
    this.typeService.addType(
      this.typeName.value,
      this.time.value,
      this.price.value,
      this.active.value,
      this.exclusive.value,
      this.amountOfImages.value,
      this.locations.value)
      .subscribe(_ => this.router.navigateByUrl('/types'))
  }

  getErrorMessage(field) {
    return field.hasError('required') ? 'You must enter a value' :
      field.hasError('email') ? 'You must enter a valid email' :
        field.hasError('pattern') ? 'You must enter a valid value' :
          '';
  }

  getMyLocations() {
    this.locationService.getMyLocations()
      .subscribe(locations => this.myLocations = locations)
  }


}
