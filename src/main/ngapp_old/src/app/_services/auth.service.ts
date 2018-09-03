import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "app/_models/user";
import {Observable, of, Subject} from "rxjs/index";
import {catchError, map} from "rxjs/internal/operators";
import {ActionResponse} from "@app/_models/action-response";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private logger = new Subject<boolean>();
  private jwtHelper;
  constructor(private http: HttpClient) {
    this.jwtHelper = new JwtHelperService();
  }

  login(email: string, password: string): Observable<string> {
    let url = 'api/v1/authentication';
    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });
    return this.http.post<string>(url, new HttpParams()
        .set('email', email)
        .set('password', password)
        .toString(),
      {headers: httpHeaders})
      .pipe(
        catchError(this.handleError('login')),
        map(string => {
        if (string) {
          this.logger.next(true);
          localStorage.setItem('token', JSON.stringify('Bearer ' + string));
        } else {
          console.log('kek')
        }
        return string;
      }));
  }

  getMe() {
    let url = 'api/v1/users/me';
    let httpHeaders = new HttpHeaders({
      'Authorization': JSON.parse(localStorage.getItem('token')),
    });
    return this.http.get<any>(url, {headers: httpHeaders})
      .pipe(map(string => {
        return string;
      }));
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    this.logger.next(false);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  isLoggedIn(): Observable<boolean> {
    return this.logger.asObservable();
  }

  register(firstName: string, lastName: string, email: string, phoneNumber: string, street: string, houseNumber: string, zipCode: string, city: string, country: string, password: string, middleName?: string): Observable<User> {
    let url = 'api/v1/users';
    return this.http.post<User>(url, {
      firstName: firstName,
      middleName: middleName,
      lastName: lastName,
      email: email,
      phoneNumber: phoneNumber,
      street: street,
      houseNumber: houseNumber,
      zipCode: zipCode,
      city: city,
      country: country,
      password: password});
  }

  updateSelf() {
    this.getMe()
      .subscribe(user =>
      localStorage.setItem('user', JSON.stringify(user)));
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
