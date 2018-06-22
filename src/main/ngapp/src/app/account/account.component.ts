import { Component, OnInit } from '@angular/core';
import {User} from "@app/_models/user";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  user: User;
  constructor() {
    this.user = JSON.parse(localStorage.getItem('user'))
  }

  ngOnInit() {
  }

}
