import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "@app/_models/user";
import {Type} from "@app/_models/type";
import {TypeService} from "@app/_services/type.service";
import Util from "@app/_util/util";
import {PromotionService} from "@app/_services/promotion.service";

@Component({
  selector: 'app-promotion-stepper',
  templateUrl: './promotion-stepper.component.html',
  styleUrls: ['./promotion-stepper.component.scss']
})
export class PromotionStepperComponent implements OnInit {
  formGroup: FormGroup;
  user: User;
  types: Type[] = [];
  currentType: Type;
  toDate: Date;

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(
    private _FormBuilder: FormBuilder,
    private typeService: TypeService,
    private promotionService: PromotionService,
  ) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.formGroup = this._FormBuilder.group({
      formArray: this._FormBuilder.array([
        this._FormBuilder.group({
          promotionName: ['', Validators.required],
          location: ['', Validators.required],
          type: ['', Validators.required]
        }),
        this._FormBuilder.group({
          startDate: ['', Validators.required]
        })
      ])
    })
  }

  onNewLocation(event) {
    this.typeService.getTypesByLocationId(event)
      .subscribe(types => this.types = types);
  }

  onNewType(event) {
    for (let i=0; i < this.types.length; i++) {
      if (this.types[i].id === event) {
        this.currentType = this.types[i];
      }
    }
  }

  newDate(event) {
    this.toDate = Util.addDays(event.value, this.currentType.time);
  }

  finishSetUp() {
    console.log(this.formArray.get([0]).value['promotionName']);
    this.promotionService.addPromotion(
      this.formArray.get([0]).value['promotionName'],
      this.formArray.get([0]).value['type'],
      this.formArray.get([0]).value['location'],
      this.user.id,
      this.formArray.get([1]).value['startDate']);
  }
}
