import { Component, OnInit } from '@angular/core';
import {User} from "@app/_models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TypeService} from "@app/_services/type.service";
import {Router} from "@angular/router";
import {Promotion} from "@app/_models/promotion";
import {PromotionService} from "@app/_services/promotion.service";

@Component({
  selector: 'app-type-stepper',
  templateUrl: './type-stepper.component.html',
  styleUrls: ['./type-stepper.component.scss']
})
export class TypeStepperComponent implements OnInit {
  user: User;
  isLinear = true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  promotions: Promotion[] = [];
  constructor(private _FormBuilder: FormBuilder, private typeService: TypeService, private router: Router, private promotionService: PromotionService) {
    this.user = JSON.parse(localStorage.getItem("user"));
  }

  ngOnInit() {
    this.firstFormGroup = this._FormBuilder.group({
      typeName: ['', Validators.required],
      active: [true],
      location: ['', [Validators.required]],
    });
    this.secondFormGroup = this._FormBuilder.group({
      time: ['', Validators.required],
      price: ['', Validators.required],
      amountOfImages: [''],
      exclusive: [false],
    });
    if (this.user.locations.length === 1) {
      this.firstFormGroup.get('location').setValue(this.user.locations[0]['id'])
    }
  }

  finishSetUp() {
    this.typeService.addType(this.firstFormGroup.get('typeName').value, this.secondFormGroup.get('time').value,
      this.secondFormGroup.get('price').value, this.firstFormGroup.get('active').value,
      this.secondFormGroup.get('exclusive').value, this.secondFormGroup.get('amountOfImages').value,
      this.firstFormGroup.get('location').value)
      .subscribe(_ => {
        this.router.navigateByUrl('/types')
      });
  }

  getLocationNameById(id: number) {
    for (let i = 0; i < this.user.locations.length; i++) {
      if (this.user.locations[i]['id'] === id) {
        return this.user.locations[i]['name'];
      }
    }
  }
}
