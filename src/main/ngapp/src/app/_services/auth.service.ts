import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "app/_models/user";
import {Observable, of, Subject} from "rxjs/index";
import {map} from "rxjs/internal/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private logger = new Subject<boolean>();
  constructor(private http: HttpClient) {
  }

  login(email: string, password: string) {
    let url = 'api/login';
    return this.http.post<User>(url, {'email': email, 'password': password})
      .pipe(map(user => {
        if (user) {
          this.logger.next(true);
          localStorage.setItem('user', JSON.stringify(user))
        }
        return user;
      }));
  }

  logout() {
    // let url = 'api/logout';
    // return this.http.get(url);
    this.logger.next(false);
    localStorage.removeItem('user');
  }

  isLoggedIn(): Observable<boolean> {
    return this.logger.asObservable();
  }

  getCurrentUser(): Observable<User> {
    // return of({"id":1,"firstName":"Joost","middleName":"","lastName":"Lekkerkerker","email":"","roles":["SUPAUSA", "Owner"],"locations":[],"promotions":[],"images":[],"phoneNumber":"","street":"","houseNumber":"","zipCode":"","city":"","country":""} as User);
    return;
  }
}
