import { Injectable } from '@angular/core';
import { Screen } from '@app/_models/screen'
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";
@Injectable({
  providedIn: 'root'
})
export class ScreenService {
  SCREENS: Screen[] = [
    new Screen(1, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, true),
    new Screen(2, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, false),
    new Screen(3, 'Scherm 1', 1080, 1920, {'id': 1, 'name': 'Cafetaria Vikas'}, false),
  ];
  constructor() { }

  getScreens(): Observable<Screen[]> {
    return of(this.SCREENS);
  }

  addScreen(name: string, height: number, width: number, locationId: number, thirdParty: boolean) {
    this.SCREENS.push(new Screen(this.SCREENS.length + 1, name, height, width, {name: 'CAFETARIA VIKAS', id: locationId}, thirdParty))
  }
}
