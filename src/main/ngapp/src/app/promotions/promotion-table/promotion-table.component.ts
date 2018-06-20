import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {PromotionTableDataSource, PromotionTableItem} from './promotion-table-datasource';
import {PromotionService} from "@app/_services/promotion.service";

@Component({
  selector: 'promotion-table',
  templateUrl: './promotion-table.component.html',
  styleUrls: ['./promotion-table.component.css']
})
export class PromotionTableComponent implements OnInit {
  dataSource: PromotionTableDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'type', 'user', 'images', 'startDate', 'more'];

  constructor (private promotionService: PromotionService) {
  }
  ngOnInit() {
    this.dataSource = new PromotionTableDataSource(this.promotionService.getPromotions());
  }
}
