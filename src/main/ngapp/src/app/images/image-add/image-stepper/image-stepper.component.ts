import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PromotionService} from "@app/_services/promotion.service";
import {Promotion} from "@app/_models/promotion";
import {Screen} from "@app/_models/screen";
import {ImageService} from "@app/_services/image.service";
import {User} from "@app/_models/user";
import {FileUploader} from "ng2-file-upload";
import { Location } from '@angular/common';
import {ScreenService} from "@app/_services/screen.service";

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
  screens: Screen[] = [];
  uploader: FileUploader = new FileUploader({url: 'api', itemAlias: 'screen'});

  get formArray(): AbstractControl | null { return this.formGroup.get('formArray'); }

  constructor(private _formBuilder: FormBuilder,
              private promotionService: PromotionService,
              private imageService: ImageService,
              private location: Location,
              private screenService: ScreenService) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.formGroup = this._formBuilder.group({
      formArray: this._formBuilder.array([
        this._formBuilder.group({
          imageName: ['', Validators.required],
          timeSlider: [0, Validators.required],
          selectPromotion: ['', Validators.required],
          selectScreen: ['', Validators.required],
        }),
        this._formBuilder.group({
          file: ['', Validators.required],
        })
      ])
    });
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log('ImageUpload:uploaded:', item, status, response);
      alert('File uploaded successfully');
    };
  }

  update_time(event) {
    this.time = event;
  }

  finishSetUp() {
    this.imageService.addImage(
      this.formArray.get([0]).value['imageName'],
      this.formArray.get([0]).value['selectPromotion'],
      this.formArray.get([0]).value['selectScreen'],
      this.user.id,
      1080,
      1920,
      ' ',
      this.formArray.get([0]).value['timeSlider'])
      .subscribe(_ => {
        this.goBack();
      });
  }

  goBack() {
    this.location.back();
  }

  onNewPromotion(event) {
    this.screenService.getScreenByPromotionId(event)
      .subscribe(screens => this.screens = screens)
  }
}


