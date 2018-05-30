import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PromotionService} from "@app/_services/promotion.service";
import {Promotion} from "@app/_models/promotion";
import {ImageService} from "@app/_services/image.service";

@Component({
  selector: 'app-image-stepper',
  templateUrl: './image-stepper.component.html',
  styleUrls: ['./image-stepper.component.scss']
})
export class ImageStepperComponent implements OnInit {
  isLinear = true;
  time = 0;
  newPromotion = true;
  promotions: Promotion[];
  detailsFormGroup: FormGroup;
  promotionFormGroup: FormGroup;
  newPromotionFormGroup: FormGroup;
  fileFormGroup: FormGroup;
  newPromo: Promotion;


  constructor(private _formBuilder: FormBuilder, private promotionService: PromotionService, private imageService: ImageService) { }

  ngOnInit() {
    this.getPromotions();
    this.detailsFormGroup = this._formBuilder.group({
      imageName: ['', Validators.required],
      time_slider: [0, Validators.required]
    });
    this.promotionFormGroup = this._formBuilder.group({
      select_promotion: ['', []]
    });
    this.newPromotionFormGroup = this._formBuilder.group({
      promotionName: ['', Validators.required]
    });
    this.fileFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  getPromotions(): void {
    this.promotionService.getPromotions()
      .subscribe(promotions => this.promotions = promotions);
  }

  check_promotion(event) {
    this.newPromotion = event === undefined;
  }

  update_time(event) {
    this.time = event;
  }

  create_promotion(): void {
    // this.newPromo = this.promotionService.addPromotion(this.newPromotionFormGroup.value['promotionName']);
  }

  finishSetUp() {
    let promo;
    if (this.newPromotion) {
      promo = this.newPromo;
    } else {
      promo = this.promotions[this.promotionFormGroup.value['select_promotion'] - 1]
    }
    this.imageService.addImage(this.detailsFormGroup.value['imageName'], promo, 1, 1080, 1920, ' ', this.time);
  }
}


