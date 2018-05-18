import { Injectable } from '@angular/core';
import {User} from "@app/users/shared/user";
import {AuthService} from "@app/auth/shared/auth.service";
import {Observable, of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private authService: AuthService) { }

  getCurrentUser(): Observable<User> {
    if (sessionStorage.getItem("user")) {
      return of(JSON.parse(sessionStorage.getItem("user")) as User);
    } else {
      return this.authService.getCurrentUser()
    }

  }

  getUsers(): Observable<User[]> {
    return of([new User(1, 'joost', '', 'Lekkerkerker', 'joostlek@outlook.com', ['SUPAUSA', "Owner"], [], [], [], '0645592066', 'Esdoornlaan', '1','3481BH', 'Harmelen', 'The Netherlands' )]);
  }
}
