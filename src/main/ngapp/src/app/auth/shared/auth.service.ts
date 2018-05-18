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

  getCurrentUser(): Observable<User> {
    return of({"id":1,"first_name":"Joost","middle_name":"","last_name":"Lekkerkerker","email":"","role":"","locations":[],"promotions":[],"images":[],"phone_number":"","street":"","house_number":"","zip_code":"","city":"","country":""} as User);
  }
}
