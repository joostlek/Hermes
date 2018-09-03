import { Component, OnInit } from '@angular/core';
import {TypeService} from "@app/_services/type.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Type} from "@app/_models/type";
import { Location } from "@angular/common";
import {User} from "@app/_models/user";
import {MatDialog} from "@angular/material";
import {DeleteAlertComponent} from "@app/_dialogs/delete-alert/delete-alert.component";

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styleUrls: ['./type.component.scss']
})
export class TypeComponent implements OnInit {
  type: Type;
  formGroup: FormGroup;
  edit: boolean = false;
  user: User;
  constructor(private route: ActivatedRoute,
              private typeService: TypeService,
              private location: Location,
              private _FormBuilder: FormBuilder,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getType();
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  getType() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.typeService.getType(id)
      .subscribe(type => {
        this.type = type;
        this.formGroup = this._FormBuilder.group({
          name: [this.type.name, Validators.required],
          time: [this.type.time, Validators.required],
          price: [this.type.price, Validators.required],
          amountOfImages: [this.type.amountOfImages, Validators.required],
          location: [{value: this.type.location['name'], disabled: true}],
          active: [this.type.active],
          exclusive: [this.type.exclusive]
        })
      });
  }

  goBack(): void {
    this.location.back();
  }

  askDelete(): void {
    let dialogRef = this.dialog.open(DeleteAlertComponent, {
      width: '300px',
      data: {delete: this.deleteType, name: this.type.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteType();
      }
    })
  }

  deleteType(): void {
    this.typeService.deleteType(this.type);
    this.location.back();
  }

  editType(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    console.log(this.formGroup.value['active'])
    this.type.name = this.formGroup.value['name'];
    this.type.time = +this.formGroup.value['time'];
    this.type.price = this.formGroup.value['price'];
    this.type.amountOfImages = this.formGroup.value['amountOfImages'];
    this.type.active = this.formGroup.get('active').value;
    this.type.exclusive = this.formGroup.value['exclusive'];
    this.typeService.updateType(this.type)
      .subscribe(_ => console.log(_));
  }

}
