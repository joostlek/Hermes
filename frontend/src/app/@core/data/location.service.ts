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

    addLocation(name: string): Observable<Location> {
        const location = new Location(name);
        return this.http.post<Location>('api/users/1/locations', location);
    }

    getPersonalLocationsByUser(user: User): Observable<Location[]> {
        return this.getPersonalLocationsByUserId(user.id);
    }

    getPersonalLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/personal-locations');
    }
}
