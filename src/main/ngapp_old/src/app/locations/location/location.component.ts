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
  }

  getLocation() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.locationService.getLocation(id)
      .subscribe(location => {
        this.location = location;
        this.formGroup = this._FormBuilder.group({
          name: [location.name, Validators.required],
          user: [{value: this.location.owner['firstName'], disabled: true}],
          street: [location.street, Validators.required],
          houseNumber: [location.houseNumber, Validators.required],
          zipCode: [location.zipCode, Validators.required],
          city: [location.city, Validators.required],
          country: [location.country, Validators.required],
        })
      });
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

  editLocation(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.location.name = this.formGroup.value['name'];
    this.location.street = this.formGroup.value['street'];
    this.location.houseNumber = this.formGroup.value['houseNumber'];
    this.location.zipCode = this.formGroup.value['zipCode'];
    this.location.city = this.formGroup.value['city'];
    this.location.country = this.formGroup.value['country'];
    this.locationService.updateLocation(this.location)
      .subscribe(_ => {
        console.log(_);
        this.edit = false;
      });
  }

}
