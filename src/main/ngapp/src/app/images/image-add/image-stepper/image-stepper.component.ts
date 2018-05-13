import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PromotionService} from "@app/promotions/shared/promotion.service";
import {Promotion} from "@app/promotions/shared/promotion.model";

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


  constructor(private _formBuilder: FormBuilder, private promotionService: PromotionService) { }

  ngOnInit() {
    this.getPromotions();
    this.detailsFormGroup = this._formBuilder.group({
      imageName: ['', Validators.required],
      time_slider: [0, Validators.required]
    });
    this.promotionFormGroup = this._formBuilder.group({
      select_promotion: ['', []]
    });
    this.promotionFormGroup.get('select_promotion');
    this.newPromotionFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
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
}


