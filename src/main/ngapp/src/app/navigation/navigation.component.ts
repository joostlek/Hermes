import {Component, Input, OnInit} from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import {User} from "@app/users/shared/user";
import {UserService} from "@app/users/shared/user.service";

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  @Input() title: string;
  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(Breakpoints.Handset);
  user: User;
  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService) {}
  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.userService.getCurrentUser()
      .subscribe(user => this.user = user);
  }

  hasPermission(roles: string[]): boolean {
    if (this.isLoggedIn()) {
      for (let i = 0; i < this.user.roles.length; i++) {
        if (this.user.roles.indexOf(roles[i]) > -1) {
          return true;
        }
      }
    }
    return false;
  }

  isLoggedIn(): boolean {
    return !!this.user;
  }
}
