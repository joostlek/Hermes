import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {UserTableDataSource, UserTableItem} from './user-table-datasource';
import {UserService} from "@app/services/user.service";

@Component({
  selector: 'user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() users: UserTableItem[];
  dataSource: UserTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'role'];

  constructor() {  }

  ngOnInit() {
    this.dataSource = new UserTableDataSource(this.paginator, this.sort, this.users);
  }
}
