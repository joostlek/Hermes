import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "@app/users/shared/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(email:string, password:string ) {
    let url = 'api/login';
    return this.http.post<User>(url, {'email': email, 'password': password})
    // this is just the HTTP call,
    // we still need to handle the reception of the toke);
  }
}
