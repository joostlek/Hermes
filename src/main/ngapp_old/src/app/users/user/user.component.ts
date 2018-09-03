import { Component, OnInit } from '@angular/core';
import {User} from "@app/_models/user";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "@app/_services/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  user: User;
  formGroup: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private _FormBuilder: FormBuilder,
    private userService: UserService,

  ) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id)
      .subscribe(user => {
        this.user = user;
        this.fillForm();
      })
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
