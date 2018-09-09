import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Location} from './domain/location';
import {filter, share} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  protected currentLocation$: BehaviorSubject<Location> = new BehaviorSubject(null);

  constructor() {
  }

  getCurrentLocation(): Location {
    return this.currentLocation$.getValue();
  }

  onCurrentLocationChange(): Observable<Location> {
    return this.currentLocation$
      .pipe(
        filter(value => !! value),
        share(),
      );
  }
}
