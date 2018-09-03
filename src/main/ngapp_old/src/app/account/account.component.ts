import { Component, OnInit } from '@angular/core';
import {User} from "@app/_models/user";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  user: User;
  formGroup: FormGroup;
  constructor(
    private _FormBuilder: FormBuilder,
  ) {
    this.user = JSON.parse(localStorage.getItem('user'))
  }

  ngOnInit() {
    this.fillForm();
  }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      firstName: [{value: this.user.firstName, disabled: true}],
      middleName: [{value: this.user.middleName, disabled: true}],
      lastName: [{value: this.user.lastName, disabled: true}],
      email: [{value: this.user.email, disabled: true}],
      phoneNumber: [{value: this.user.phoneNumber, disabled: true}],
      street: [{value: this.user.street, disabled: true}],
      houseNumber: [{value: this.user.houseNumber, disabled: true}],
      zipCode: [{value: this.user.zipCode, disabled: true}],
      city: [{value: this.user.city, disabled: true}],
      country: [{value: this.user.country, disabled: true}],
    })
  }
}
