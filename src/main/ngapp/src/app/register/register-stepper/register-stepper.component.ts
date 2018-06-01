import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Type} from "@app/_models/type";
import {TypeService} from "@app/_services/type.service";
import {PromotionService} from "@app/_services/promotion.service";
import Util from "@app/_util/util";
import {User} from "@app/_models/user";
import {AuthService} from "@app/_services/auth.service";

@Component({
  selector: 'app-register-stepper',
  templateUrl: './register-stepper.component.html',
  styleUrls: ['./register-stepper.component.scss']
})
export class RegisterStepperComponent implements OnInit {
  formGroup: FormGroup;

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(
    private _FormBuilder: FormBuilder,
    private authService: AuthService,
  ) {  }

  ngOnInit() {
    this.formGroup = this._FormBuilder.group({
      formArray: this._FormBuilder.array([
        this._FormBuilder.group({
          firstName: ['', Validators.required],
          middleName: [''],
          lastName: ['', Validators.required],
          email: ['', [Validators.email, Validators.required]],
          phoneNumber: ['', Validators.required],
          street: ['', Validators.required],
          houseNumber: ['', Validators.required],
          zipCode: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required],
          password: ['', Validators.required],
        })
      ])
    })
  }

  finishSetUp() {
    this.authService.register(
      this.formArray.get([0]).value['firstName'],
      this.formArray.get([0]).value['lastName'],
      this.formArray.get([0]).value['email'],
      this.formArray.get([0]).value['phoneNumber'],
      this.formArray.get([0]).value['street'],
      this.formArray.get([0]).value['houseNumber'],
      this.formArray.get([0]).value['zipCode'],
      this.formArray.get([0]).value['city'],
      this.formArray.get([0]).value['country'],
      this.formArray.get([0]).value['password'],
      this.formArray.get([0]).value['middleName']
    )
      .subscribe(function (actionResponse) {
        if (actionResponse.code === 200) {
          console.log("yes");
        }
      })
  }
}
