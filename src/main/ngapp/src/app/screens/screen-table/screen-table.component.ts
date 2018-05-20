import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {ScreenTableDataSource, ScreenTableItem} from './screen-table-datasource';

@Component({
  selector: 'screen-table',
  templateUrl: './screen-table.component.html',
  styleUrls: ['./screen-table.component.css']
})
export class ScreenTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() screens: ScreenTableItem[];
  dataSource: ScreenTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'width', 'height', 'location', 'more'];

  ngOnInit() {
    this.dataSource = new ScreenTableDataSource(this.paginator, this.sort, this.screens);
  }
}
