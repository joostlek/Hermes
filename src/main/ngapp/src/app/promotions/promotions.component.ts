import { Component, OnInit } from '@angular/core';
import {PromotionTableItem} from "@app/promotions/promotion-table/promotion-table-datasource";
import {PromotionService} from "@app/services/promotion.service";

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.scss']
})
export class PromotionsComponent implements OnInit {
  promotions: PromotionTableItem[];
  constructor(private promotionService: PromotionService) { }

  ngOnInit() {
    this.promotionService.getPromotions()
      .subscribe(promotions => this.promotions = promotions.map(promotion => promotion.toTable()));
  }

}
