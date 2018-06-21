import { Injectable } from '@angular/core';
import { Screen } from '@app/_models/screen'
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";
import {HttpClient, HttpHeaders} from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class ScreenService {
  SCREENS: Screen[] = [
    new Screen(1, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, true),
    new Screen(2, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, false),
    new Screen(3, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, false),
  ];
  constructor(private http: HttpClient) { }

  getScreens(): Observable<Screen[]> {
    return of(this.SCREENS);
  }

  addScreen(name: string, height: number, width: number, locationId: number, thirdParty: boolean) {
    this.SCREENS.push(new Screen(this.SCREENS.length + 1, name, height, width, {name: 'CAFETARIA VIKAS', id: locationId}, thirdParty))
  }

  getScreen(id: number): Observable<Screen> {
    for (let i=0; i < this.SCREENS.length; i++) {
      if (this.SCREENS[i].id === id) {
        return of(this.SCREENS[i]);
      }
    }
  }

  deleteScreen(screen: Screen) {
    for (let i=0; i < this.SCREENS.length; i++) {
      if (this.SCREENS[i].id === screen.id) {
        this.SCREENS.splice(i,1);
        return;
      }
    }
  }

  editScreen(screen: Screen): void {
    for (let i=0; i < this.SCREENS.length; i++) {
      if (this.SCREENS[i].id === screen.id) {
        this.SCREENS[i] = screen;
        return;
      }
    }
  }

  getScreenByPromotionId(id: number): Observable<Screen[]> {
    let url = '/api/v1/screens/promotion/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Screen[]>(url + id, {headers: httpHeaders});
  }
}
