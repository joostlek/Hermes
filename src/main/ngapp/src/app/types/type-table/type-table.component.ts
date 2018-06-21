import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import {TypeTableDataSource} from './type-table-datasource';
import {TypeService} from "@app/_services/type.service";
import {User} from "@app/_models/user";
import {Roles} from "@app/_models/roles";

@Component({
  selector: 'type-table',
  templateUrl: './type-table.component.html',
  styleUrls: ['./type-table.component.css']
})
export class TypeTableComponent implements OnInit {
  dataSource: TypeTableDataSource;
  user: User;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'time', 'price', 'active', 'exclusive', 'amountOfImages', 'location', 'more'];

  constructor(private typeService: TypeService){
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    if (this.user.role == Roles.ROLE_SUPERUSER) {
      this.dataSource = new TypeTableDataSource(this.typeService.getTypes());
    } else {
      this.dataSource = new TypeTableDataSource(this.typeService.getMyTypes());
    }
  }
}
