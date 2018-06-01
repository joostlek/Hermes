import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "app/_models/user";
import {Observable, of, Subject} from "rxjs/index";
import {map} from "rxjs/internal/operators";
import {ActionResponse} from "@app/_models/action-response";

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

  register(firstName: string, lastName: string, email: string, phoneNumber: string, street: string, houseNumber: string, zipCode: string, city: string, country: string, password: string, middleName?: string): Observable<ActionResponse> {
    let url = 'api/register';
    return this.http.post<ActionResponse>(url, {firstName: firstName, middleName: middleName, lastName: lastName, email: email, phoneNumber: phoneNumber, street: street, houseNumber: houseNumber, zipCode: zipCode, city: city, country: country, password: password});

  }
}
