import { Component, OnInit } from '@angular/core';
import { Location as Loco} from '@angular/common';
import { Location } from '@app/_models/location';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ScreenService} from "@app/_services/screen.service";
import {LocationService} from "@app/_services/location.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-screen-add',
  templateUrl: './screen-add.component.html',
  styleUrls: ['./screen-add.component.scss']
})
export class ScreenAddComponent implements OnInit {
  formGroup: FormGroup;
  locations: Location[] = [];

  constructor(
    private loco: Loco,
    private screenService: ScreenService,
    private locationService: LocationService,
    private _FormBuilder: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit() {
    this.fillForm();
    this.getLocations();
  }

  get screenName() { return this.formGroup.get('screenName'); }

  get screenHeight() { return this.formGroup.get('screenHeight'); }

  get screenWidth() { return this.formGroup.get('screenWidth'); }

  get location() { return this.formGroup.get('location'); }

  get thirdParty() { return this.formGroup.get('thirdParty'); }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      screenName: ['', Validators.required],
      screenHeight: ['', Validators.required],
      screenWidth: ['', Validators.required],
      location: ['', Validators.required],
      thirdParty: ['']
    })
  }

  goBack() {
    this.loco.back();
  }

  getLocations() {
    this.locationService.getSimpleLocations()
      .subscribe(locations => this.locations = locations);
  }

  finish() {
    this.screenService.addScreen(
      this.screenName.value,
      this.screenHeight.value,
      this.screenWidth.value,
      this.location.value,
      this.thirdParty.value
    )
      .subscribe(_ => this.router.navigateByUrl('/screens'))
  }

}
