import { Injectable } from '@angular/core';
import {AuthService} from "@app/_services/auth.service";
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router} from "@angular/router";
import decode from 'jwt-decode';
import {Roles} from "@app/_models/roles";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanLoad {

  constructor(public auth: AuthService, public router: Router) { }

  canLoad(route: Route): boolean {
    const expectedRoles = route.data.roles;
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    if (!this.auth.isAuthenticated() || !RoleGuardService.isAllowed(JSON.parse(localStorage.getItem('user')).role, expectedRoles)) {
      if (JSON.parse(localStorage.getItem('user')) !== null) {
        this.router.navigateByUrl('/');
      } else {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        this.router.navigateByUrl('auth/login');
      }
      return false;
    }
    return true;
  }

  static isAllowed(role, roles): boolean {
    // if (role === Roles.ROLE_SUPERUSER) {
    //   return true;
    // }
    if (roles.indexOf(role) > -1) {
      return true;
    } else if (role === Roles.ROLE_OWNER_AD && (roles.indexOf(Roles.ROLE_OWNER) > -1 || roles.indexOf(Roles.ROLE_ADVERTISING) > -1)) {
      return true;
    } else return roles.indexOf(Roles.ROLE_USER) > -1 && (role !== Roles.ROLE_GUEST && role !== Roles.ROLE_SUPERUSER);
  }
}
