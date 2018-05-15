import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {TypeTableDataSource, TypeTableItem} from './type-table-datasource';

@Component({
  selector: 'type-table',
  templateUrl: './type-table.component.html',
  styleUrls: ['./type-table.component.css']
})
export class TypeTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() types: TypeTableItem[];
  dataSource: TypeTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'time', 'price', 'active', 'exclusive', 'numberOfImages', 'locationId'];

  constructor(){}

  ngOnInit() {
    this.dataSource = new TypeTableDataSource(this.paginator, this.sort, this.types);
  }
}
