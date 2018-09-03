import { Component, OnInit } from '@angular/core';
import { Location as Loco } from '@angular/common';
import { Location } from '@app/_models/location';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LocationService} from "@app/_services/location.service";
import {Type} from "@app/_models/type";
import {User} from "@app/_models/user";
import Util from "@app/_util/util";
import {TypeService} from "@app/_services/type.service";
import {PromotionService} from "@app/_services/promotion.service";

@Component({
  selector: 'app-promotion-add',
  templateUrl: './promotion-add.component.html',
  styleUrls: ['./promotion-add.component.scss']
})
export class PromotionAddComponent implements OnInit {
  formGroup: FormGroup;
  locations: Location[] = [];
  types: Type[] = [];
  currentType: Type;
  toDate: Date;
  user: User;

  constructor(
    private location: Loco,
    private _FormBuilder: FormBuilder,
    private router: Router,
    private locationService: LocationService,
    private typeService: TypeService,
    private promotionService: PromotionService,
  ) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.fillForm();
    this.getSimpleLocations();
  }

  get promotionName() { return this.formGroup.get('promotionName'); }

  get locationId() { return this.formGroup.get('location'); }

  get typeId() { return this.formGroup.get('type'); }

  get startDate() { return this.formGroup.get('startDate'); }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      promotionName: ['', Validators.required],
      location: ['', Validators.required],
      type: ['', Validators.required],
      startDate: ['', Validators.required]
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

  getSimpleLocations() {
    this.locationService.getSimpleLocations()
      .subscribe(locations => this.locations = locations)
  }

  goBack() {
    this.location.back();
  }

  finish() {
    this.promotionService.addPromotion(
      this.promotionName.value,
      this.typeId.value,
      this.locationId.value,
      this.user.id,
      this.startDate.value,
    )
      .subscribe(_ =>
        this.router.navigateByUrl('/promotions')
      )
  }


  getErrorMessage(field) {
    return field.hasError('required') ? 'You must enter a value' :
      field.hasError('email') ? 'You must enter a valid email' :
        field.hasError('pattern') ? 'You must enter a valid value' :
          '';
  }

}
