import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "@app/_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;


  constructor(
    private location: Location,
    private _FormBuilder: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.registerForm = this._FormBuilder.group({
      firstName: ['', Validators.required],
      middleName: [],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^(?=^.{10,11}$)0\\d*-?\\d*$')]],
      street: ['', Validators.required],
      houseNumber: ['', [Validators.required, Validators.pattern('[1-9]+[a-zA-Z]*')]],
      zipCode: ['', [Validators.required, Validators.pattern('[1-9][0-9]{3} ?(?!sa|sd|ss|SA|SD|SS)[a-zA-Z]{2}$')]],
      city: ['', Validators.required],
      country: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  get firstName() { return this.registerForm.get('firstName');}

  get middleName() { return this.registerForm.get('middleName');}

  get lastName() { return this.registerForm.get('lastName');}

  get email() { return this.registerForm.get('email');}

  get phoneNumber() { return this.registerForm.get('phoneNumber');}

  get street() { return this.registerForm.get('street');}

  get houseNumber() { return this.registerForm.get('houseNumber');}

  get zipCode() { return this.registerForm.get('zipCode');}

  get city() { return this.registerForm.get('city');}

  get country() { return this.registerForm.get('country');}

  get password() { return this.registerForm.get('password');}


  goBack() {
    this.location.back();
  }

  getErrorMessage(field) {
    return field.hasError('required') ? 'You must enter a value' :
      field.hasError('email') ? 'You must enter a valid email' :
        field.hasError('pattern') ? 'You must enter a valid value' :
          '';
  }

  finish() {
    if (this.registerForm.valid) {
      this.auth.register(
        this.firstName.value,
        this.lastName.value,
        this.email.value,
        this.phoneNumber.value,
        this.street.value,
        this.houseNumber.value,
        this.zipCode.value,
        this.city.value,
        this.country.value,
        this.password.value,
        this.middleName.value
      )
        .subscribe(user  => {
          if (user.id) {
            this.router.navigateByUrl('/auth/login');
          }
        })
    }
  }

}
