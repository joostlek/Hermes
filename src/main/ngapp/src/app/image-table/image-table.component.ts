import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { ImageTableDataSource } from './image-table-datasource';

@Component({
  selector: 'image-table',
  templateUrl: './image-table.component.html',
  styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: ImageTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit() {
    this.dataSource = new ImageTableDataSource(this.paginator, this.sort);
  }
}
