import { Injectable } from '@angular/core';
import { Screen } from '@app/models/screen'
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";
@Injectable({
  providedIn: 'root'
})
export class ScreenService {
  SCREENS: Screen[] = [
    new Screen(1, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}),
    new Screen(2, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}),
    new Screen(3, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}),
  ];
  constructor() { }

  getScreens(): Observable<Screen[]> {
    return of(this.SCREENS);
  }
}
