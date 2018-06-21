import { Injectable } from '@angular/core';
import { Screen } from '@app/_models/screen'
import {Observable} from "rxjs/internal/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class ScreenService {

  constructor(private http: HttpClient) { }

  getScreens(): Observable<Screen[]> {
    let url = 'api/v1/screens/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Screen[]>(url, {headers: httpHeaders});
  }

  addScreen(name: string, height: number, width: number, locationId: number, thirdParty: boolean): Observable<Screen> {
    let screen = {
      name: name,
      height: height,
      width: width,
      allowAds: thirdParty,
      location: {
        id: locationId
      }
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    let url = 'api/v1/screens';
    return this.http.post<Screen>(url, screen, {headers: httpHeaders});
  }

  getScreen(id: number): Observable<Screen> {
    let url = 'api/v1/screens/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Screen>(url + id, {headers: httpHeaders});
  }

  deleteScreen(screen: Screen) {
    let url = 'api/v1/screens/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': screen.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
  }

  editScreen(screen: Screen): Observable<Screen> {
    let url = 'api/v1/screens';
    let scr = {
      id: screen.id,
      name: screen.name,
      height: +screen.height,
      width: +screen.width,
      allowAds: screen.allowAds
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.put<Screen>(url, scr, {headers: httpHeaders});
  }

  getScreenByPromotionId(id: number): Observable<Screen[]> {
    let url = '/api/v1/screens/promotion/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Screen[]>(url + id, {headers: httpHeaders});
  }
}
