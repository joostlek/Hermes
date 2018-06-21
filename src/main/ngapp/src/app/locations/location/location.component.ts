import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material";
import {Location as loco} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@app/_models/location";
import {DeleteAlertComponent} from "@app/_dialogs/delete-alert/delete-alert.component";
import {LocationService} from "@app/_services/location.service";

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {
  location: Location;
  formGroup: FormGroup;
  edit: boolean = false;

  constructor(private route: ActivatedRoute,
              private locationService: LocationService,
              private loco: loco,
              private _FormBuilder: FormBuilder,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getLocation();

    this.formGroup = this._FormBuilder.group({
      name: [Validators.required],
      street: [Validators.required],
      houseNumber: [Validators.required],
      zipCode: [Validators.required],
      city: [Validators.required],
      country: [Validators.required],
    })
  }

  getLocation() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.locationService.getLocation(id)
      .subscribe(location => this.location = location);
  }

  goBack(): void {
    this.loco.back();
  }

  askDelete(): void {
    let dialogRef = this.dialog.open(DeleteAlertComponent, {
      width: '300px',
      data: {delete: this.deleteLocation, name: this.location.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteLocation();
      }
    })
  }

  deleteLocation(): void {
    this.locationService.deleteLocation(this.location);
    this.loco.back();
  }

  editType(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    this.location.name = this.formGroup.value['name'];
    this.location.street = this.formGroup.value['street'];
    this.location.houseNumber = this.formGroup.value['houseNumber'];
    this.location.zipCode = this.formGroup.value['zipCode'];
    this.location.city = this.formGroup.value['city'];
    this.location.country = this.formGroup.value['country'];
    this.locationService.updateLocation(this.location);
  }

}
