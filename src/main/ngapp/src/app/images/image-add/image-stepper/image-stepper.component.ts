import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-image-stepper',
  templateUrl: './image-stepper.component.html',
  styleUrls: ['./image-stepper.component.scss']
})
export class ImageStepperComponent implements OnInit {
  isLinear = true;
  detailsFormGroup: FormGroup;
  promotionFormGroup: FormGroup;
  fileFormGroup: FormGroup;
  timeFormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit() {
    this.detailsFormGroup = this._formBuilder.group({
      imageName: ['', Validators.required]
    });
    this.promotionFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.fileFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.timeFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

}
