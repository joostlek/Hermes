import { Component, OnInit } from '@angular/core';
import {TypeService} from "@app/services/type.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Type} from "@app/models/type";
import { Location } from "@angular/common";
import {User} from "@app/models/user";

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
              private _FormBuilder: FormBuilder) { }

  ngOnInit() {
    this.getType();
    this.user = JSON.parse(localStorage.getItem('user'));
    this.formGroup = this._FormBuilder.group({
      name: [this.type.name, Validators.required],
      time: [this.type.time, Validators.required],
      price: [this.type.price, Validators.required],
      imageCount: [this.type.imageCount, Validators.required],
      location: [this.type.location['id'], Validators.required],
      active: [this.type.active],
      exclusive: [this.type.exclusive]
    })
  }

  getType() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.typeService.getType(id)
      .subscribe(type => this.type = type);
  }

  goBack(): void {
    this.location.back();
  }

  deleteType(): void {
    this.typeService;
    this.location.back();
  }

  editType(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    this.type.name = this.formGroup.value['name'];
    this.type.time = +this.formGroup.value['time'];
    this.type.price = this.formGroup.value['price'];
    this.type.imageCount = this.formGroup.value['imageCount'];
    this.type.location['id'] = this.formGroup.value['location'];
    this.type.active = this.formGroup.value['active'];
    this.type.exclusive = this.formGroup.value['exclusive'];
    this.typeService.updateType(this.type);
  }

}
