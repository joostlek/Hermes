import { Injectable } from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";
import { Location } from '@app/_models/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  LOCATIONS: Location[] = [
    new Location(1, 'Cafetaria Vikas', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
    new Location(2, 'Cafetaria de Snack', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
    new Location(3, 'Cafetaria de nogwat', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
  ];
  constructor() { }

  getLocations(): Observable<Location[]> {
    return of(this.LOCATIONS);
  }
}
