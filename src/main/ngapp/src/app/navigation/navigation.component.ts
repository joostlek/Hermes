import { Component, Input, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { AuthService } from "@app/_services/auth.service";
import { Roles } from "@app/_models/roles";
import {User} from "@app/_models/user";
import {RoleGuardService} from "@app/_services/role-guard.service";

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  @Input() title: string;
  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(Breakpoints.Handset);
  // TODO - remove ' = true' when in production
  loggedIn: boolean;
  user: User;
  roles = Roles;
  constructor(private breakpointObserver: BreakpointObserver, private authService: AuthService) {
    this.loggedIn = localStorage.getItem('token') !== null;
  }
  ngOnInit() {
    this.isLoggedIn();
  }

  hasPermission(roles: string[]): boolean {
    if (this.loggedIn) {
      this.user = JSON.parse(localStorage.getItem('user'));
      return RoleGuardService.isAllowed(this.user.role, roles);
    }
    return false;
  }

  isLoggedIn() {
    this.authService.isLoggedIn()
      .subscribe(bool => this.loggedIn = bool);
  }
}
