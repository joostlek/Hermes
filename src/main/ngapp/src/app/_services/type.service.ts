import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";


import { MessagingService } from "app/_services/messaging.service";
import { Type } from "app/_models/type";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  constructor(private http: HttpClient) { }

  getTypes(): Observable<Type[]> {
    MessagingService.send("Get all types");
    let url = 'api/v1/types/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Type[]>(url, {headers: httpHeaders});
  }

  getMyTypes(): Observable<Type[]> {
    MessagingService.send("Get all types");
    let url = 'api/v1/types';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Type[]>(url, {headers: httpHeaders});
  }

  getType(id: number): Observable<Type> {
    MessagingService.send("Get type " + id);
    let url = 'api/v1/types/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Type>(url + id, {headers: httpHeaders});
  }

  deleteType(type: Type): void {
    MessagingService.send("Delete type "+ type.name);
    let url = 'api/v1/types';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': type.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
  }

  addType(name: string, time:number, price: number, active: boolean, exclusive: boolean, amountOfImages: number, locationId: number): Observable<Type> {
    let type = {
      name: name,
      time: time,
      price: price,
      active: active,
      exclusive: exclusive,
      amountOfImages: amountOfImages,
      location: {
        id: locationId
      }
    };
    let url = 'api/v1/types';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.post<Type>(url, type, {headers: httpHeaders});
  }

  updateType(type: Type): Observable<Type> {
    let ty = {
      name: type.name,
      time: type.time,
      price: type.price,
      active: type.active,
      exclusive: type.exclusive,
      amountOfImages: type.amountOfImages
    };
    let url = 'api/v1/types';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.put<Type>(url, ty, {headers: httpHeaders});
  }

  getTypesByLocationId(locationId: number): Observable<Type[]> {
    let url = 'api/v1/types/location/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Type[]>(url + locationId, {headers: httpHeaders});
  }
}
