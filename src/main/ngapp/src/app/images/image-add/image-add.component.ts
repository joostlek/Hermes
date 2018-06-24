import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";
import {Screen} from "@app/_models/screen";
import {Promotion} from "@app/_models/promotion";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "@app/_models/user";
import {ImageService} from "@app/_services/image.service";
import {ScreenService} from "@app/_services/screen.service";
import {PromotionService} from "@app/_services/promotion.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-image-add',
  templateUrl: './image-add.component.html',
  styleUrls: ['./image-add.component.scss']
})
export class ImageAddComponent implements OnInit {
  promotions: Promotion[];
  screens: Screen[];
  time = 0;
  formGroup: FormGroup;
  user: User;

  constructor(
    private location: Location,
    private _FormBuilder: FormBuilder,
    private imageService: ImageService,
    private promotionService: PromotionService,
    private screenService: ScreenService,
    private router: Router,
  ) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.fillForm();
    this.getPromotions();
  }

  get imageName() { return this.formGroup.get('imageName'); }

  get timeSlider() { return this.formGroup.get('timeSlider'); }

  get selectPromotion() { return this.formGroup.get('selectPromotion'); }

  get selectScreen() { return this.formGroup.get('selectScreen'); }

  get imageURL() { return this.formGroup.get('imageURL'); }


  goBack(): void {
    this.location.back();
  }

  fillForm() {
    this.formGroup = this._FormBuilder.group({
      imageName: ['', Validators.required],
      timeSlider: ['', Validators.required],
      selectPromotion: ['', Validators.required],
      selectScreen: ['', Validators.required],
      imageURL: ['', Validators.required],
    })
  }

  getPromotions() {
    this.promotionService.getMyPromotions()
      .subscribe(promotions => {
        this.promotions = promotions
      })
  }

  onNewPromotion(event) {
    this.screenService.getScreenByPromotionId(event)
      .subscribe(screens => this.screens = screens)
  }

  update_time(event) {
    this.time = event;
  }

  finish() {
    this.imageService.addImage(
      this.imageName.value,
      this.selectPromotion.value,
      this.selectScreen.value,
      this.user.id,
      1080,
      1920,
      this.imageURL.value,
      this.time
    )
      .subscribe(_ => {
        this.router.navigateByUrl('/images')
      })
  }
}
