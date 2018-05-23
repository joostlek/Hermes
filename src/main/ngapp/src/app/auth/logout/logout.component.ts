import { Component, OnInit } from '@angular/core';
import {AuthService} from "@app/_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    localStorage.removeItem("user");
    this.authService.logout();
    this.router.navigateByUrl('/');
  }

}
