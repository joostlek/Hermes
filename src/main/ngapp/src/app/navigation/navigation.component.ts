import { Component, Input, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { AuthService } from "@app/_services/auth.service";
import {User} from "@app/_models/user";

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  @Input() title: string;
  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(Breakpoints.Handset);
  // TODO - remove ' = true' when in production
  loggedIn: boolean = false;
  user: User;
  constructor(private breakpointObserver: BreakpointObserver, private authService: AuthService) {
    this.user = JSON.parse(localStorage.getItem('user'));
    console.log(this.user);
  }
  ngOnInit() {
    this.isLoggedIn();
  }

  hasPermission(roles: string[]): boolean {
    if (this.loggedIn) {
      if (!this.user) {
        this.user = JSON.parse(localStorage.getItem('user'));
      }
      for (let i = 0; i < this.user.roles.length; i++) {
        if (this.user.roles.indexOf(roles[i]) > -1) {
          return true;
        }
      }
    }
    return false;
  }

  isLoggedIn() {
    this.authService.isLoggedIn()
      .subscribe(bool => this.loggedIn = bool);
  }
}
