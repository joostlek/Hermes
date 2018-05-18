import { Component, OnInit } from '@angular/core';
import {AuthService} from "@app/services/auth.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    localStorage.removeItem("user");
    this.authService.logout();
  }

}
