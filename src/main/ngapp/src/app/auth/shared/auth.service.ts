import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "@app/users/shared/user";
import {Observable, of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string) {
    let url = 'api/login';
    return this.http.post<User>(url, {'email': email, 'password': password});
  }

  logout() {
    let url = 'api/logout';
    return this.http.get(url);
  }

  getCurrentUser(): Observable<User> {
    // return of({"id":1,"firstName":"Joost","middleName":"","lastName":"Lekkerkerker","email":"","roles":["SUPAUSA", "Owner"],"locations":[],"promotions":[],"images":[],"phoneNumber":"","street":"","houseNumber":"","zipCode":"","city":"","country":""} as User);
    return;
  }
}
