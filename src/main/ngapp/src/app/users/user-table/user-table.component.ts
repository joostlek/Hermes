import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {UserTableDataSource} from './user-table-datasource';
import {UserService} from "@app/_services/user.service";

@Component({
  selector: 'user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {
  dataSource: UserTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'role', 'more'];

  constructor(private userService: UserService) {  }

  ngOnInit() {
    this.dataSource = new UserTableDataSource(this.userService.getUsers());
  }
}
