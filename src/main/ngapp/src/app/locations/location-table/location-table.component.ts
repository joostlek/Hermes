import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {LocationTableDataSource, LocationTableItem} from './location-table-datasource';
import {Router} from "@angular/router";

@Component({
  selector: 'location-table',
  templateUrl: './location-table.component.html',
  styleUrls: ['./location-table.component.css']
})
export class LocationTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() locations: LocationTableItem[];
  dataSource: LocationTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'ownerName', 'screens', 'more'];

  constructor () {}
  ngOnInit() {
    this.dataSource = new LocationTableDataSource(this.paginator, this.sort, this.locations);
  }
}
