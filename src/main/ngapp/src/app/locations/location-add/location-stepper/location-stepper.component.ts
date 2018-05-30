import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "@app/_models/user";
import {UserService} from "@app/_services/user.service";
import {LocationService} from "@app/_services/location.service";

@Component({
  selector: 'app-location-stepper',
  templateUrl: './location-stepper.component.html',
  styleUrls: ['./location-stepper.component.scss']
})
export class LocationStepperComponent implements OnInit {
  formGroup: FormGroup;
  users: User[] = [];

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(
    private _FormBuilder: FormBuilder,
    private userService: UserService,
    private locationService: LocationService,
  ) { }

  ngOnInit() {
    this.formGroup = this._FormBuilder.group({
      formArray: this._FormBuilder.array([
        this._FormBuilder.group({
          locationName: ['', Validators.required],
          locationOwner: ['', Validators.required],
          street: ['', Validators.required],
          houseNumber: ['', Validators.required],
          zipCode: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required]
        })
      ])
    });
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers()
      .subscribe(users => this.users = users);
  }

  finishSetUp() {
    this.locationService.addLocation(
      this.formArray.get([0]).value['locationName'],
      this.formArray.get([0]).value['locationOwner'],
      this.formArray.get([0]).value['street'],
      this.formArray.get([0]).value['houseNumber'],
      this.formArray.get([0]).value['zipCode'],
      this.formArray.get([0]).value['city'],
      this.formArray.get([0]).value['country']
    )
  }

}
