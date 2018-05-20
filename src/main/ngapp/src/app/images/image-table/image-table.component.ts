import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {ImageTableDataSource, ImageTableItem} from './image-table-datasource';

@Component({
  selector: 'image-table',
  templateUrl: './image-table.component.html',
  styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() images: ImageTableItem[];
  dataSource: ImageTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'promotion', 'user', 'width', 'height', 'active', 'more'];

  constructor () {}

  ngOnInit() {
    this.dataSource = new ImageTableDataSource(this.paginator, this.sort, this.images);
  }
}
