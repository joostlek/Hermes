import { Injectable } from '@angular/core';
import {CanActivate, CanLoad, Router} from "@angular/router";
import {AuthService} from "@app/_services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanLoad {

  constructor(public auth: AuthService, public router: Router) { }

  canLoad(): boolean {
    if (!this.auth.isAuthenticated()) {
      this.router.navigateByUrl('auth/login');
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      return false;
    }
    return true;
  }
}
