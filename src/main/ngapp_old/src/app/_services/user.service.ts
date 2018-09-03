import { Injectable } from '@angular/core';
import {User} from "app/_models/user";
import {Observable, of} from "rxjs/index";
import {Roles} from "@app/_models/roles";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getUsers(): Observable<User[]> {
    let url = 'api/v1/users/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<User[]>(url, {headers: httpHeaders});
  }

  getUser(id: number): Observable<User> {
    let url = 'api/v1/users/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<User>(url + id, {headers: httpHeaders});
  }

  deleteUser(user: User) {
    let url = 'api/v1/users/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': user.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
  }


}
