import { Component, OnInit } from '@angular/core';


import {UserTableItem} from "@app/users/user-table/user-table-datasource";
import {UserService} from "@app/users/shared/user.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: UserTableItem[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(users => this.users = users.map(user => user.toTable()));
  }

}
