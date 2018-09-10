import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of as observableOf} from 'rxjs';
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
        filter(value => !!value),
        share(),
      );
  }

  updateCurrentLocation(id: number, callback?: () => void): void {
    this.getLocationById(id)
      .subscribe((location: Location) => this.pushCurrentLocation(location),
        () => {},
        callback
      );
  }

  getLocationById(id: number): Observable<Location> {
    return observableOf(new Location(1, '', {}, [], '', '', '', '', ''));
  }

  pushCurrentLocation(location: Location): boolean {
    this.currentLocation$.next(location);
    return true;
  }
}
