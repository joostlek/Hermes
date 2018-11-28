import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Location} from './domain/location';
import {User} from './domain/user';

@Injectable({
    providedIn: 'root'
})
export class LocationService {

    constructor(private http: HttpClient) {
    }

    getAllLocations(): Observable<Location[]> {
        return this.http.get<Location[]>('api/locations');
    }

    getLocationById(id: number): Observable<Location> {
        return this.http.get<Location>('api/locations/' + id);
    }

    getLocationsByUser(user: User): Observable<Location[]> {
        return this.getLocationsByUserId(user.id);
    }

    getLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/locations');
    }
}
