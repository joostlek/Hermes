import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { PromotionTableDataSource } from './promotion-table-datasource';

@Component({
  selector: 'promotion-table',
  templateUrl: './promotion-table.component.html',
  styleUrls: ['./promotion-table.component.css']
})
export class PromotionTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: PromotionTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit() {
    this.dataSource = new PromotionTableDataSource(this.paginator, this.sort);
  }
}
