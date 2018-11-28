import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Location} from './domain/location';
import {Screen} from './domain/screen';

@Injectable({
    providedIn: 'root',
})
export class ScreenService {

    constructor(
        private http: HttpClient,
    ) {
    }

    getScreens(): Observable<Screen[]> {
        return this.http.get<Screen[]>('/api/screens');
    }

    getScreensByLocation(location: Location): Observable<Screen[]> {
        return this.getScreensByLocationId(location.id);
    }

    getScreensByLocationId(locationId: number): Observable<Screen[]> {
        return this.http.get<Screen[]>('/api/locations/' + locationId + '/screens');
    }
}
