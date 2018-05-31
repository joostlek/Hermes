import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PromotionService} from "@app/_services/promotion.service";
import {Promotion} from "@app/_models/promotion";
import {ImageService} from "@app/_services/image.service";
import {User} from "@app/_models/user";

@Component({
  selector: 'app-image-stepper',
  templateUrl: './image-stepper.component.html',
  styleUrls: ['./image-stepper.component.scss']
})
export class ImageStepperComponent implements OnInit {
  isLinear = true;
  time = 0;
  formGroup: FormGroup;
  promotions: Promotion[] = [];
  user: User;

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(private _formBuilder: FormBuilder,
              private promotionService: PromotionService,
              private imageService: ImageService) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.formGroup = this._formBuilder.group({
      formArray: this._formBuilder.array([
        this._formBuilder.group({
          imageName: ['', Validators.required],
          timeSlider: [0, Validators.required],
          selectPromotion: ['', Validators.required],
        }),
        this._formBuilder.group({
          file: ['', Validators.required],
        })
      ])
    });
  }

  update_time(event) {
    this.time = event;
  }

  finishSetUp() {
    this.imageService.addImage(
      this.formArray.get([0]).value['imageName'],
      this.formArray.get([0]).value['selectPromotion'],
      this.user.id,
      1080,
      1920,
      ' ',
      this.formArray.get([0]).value['timeSlider']);
  }
}


