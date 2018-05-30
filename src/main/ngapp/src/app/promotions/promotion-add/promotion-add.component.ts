import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-promotion-add',
  templateUrl: './promotion-add.component.html',
  styleUrls: ['./promotion-add.component.scss']
})
export class PromotionAddComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goBack() {
    this.location.back();
  }

}
