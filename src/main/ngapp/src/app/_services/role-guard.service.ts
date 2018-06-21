import { Injectable } from '@angular/core';
import {AuthService} from "@app/_services/auth.service";
import {ActivatedRouteSnapshot, CanActivate, Router} from "@angular/router";
import decode from 'jwt-decode';
import {Roles} from "@app/_models/roles";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles = route.data.roles;
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    if (!this.auth.isAuthenticated() || !RoleGuardService.isAllowed(JSON.parse(localStorage.getItem('user')).role, expectedRoles)) {
      this.router.navigateByUrl('auth/login');
      return false;
    }
    return true;
  }

  static isAllowed(role, roles): boolean {
    if (role === Roles.ROLE_SUPERUSER) {
      return true;
    }
    if (roles.indexOf(role) > -1) {
      return true;
    } else if (role === Roles.ROLE_OWNER_AD && (roles.indexOf(Roles.ROLE_OWNER) > -1 || roles.indexOf(Roles.ROLE_ADVERTISING) > -1)) {
      return true;
    } else return roles.indexOf(Roles.ROLE_USER) > -1 && role !== Roles.ROLE_GUEST;
  }
}
