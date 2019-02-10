import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CurrentUserService} from './current-user.service';
import {Location} from './domain/location';
import {User} from './domain/user';

@Injectable({
    providedIn: 'root',
})
export class LocationService {

    constructor(private http: HttpClient,
                private currentUserService: CurrentUserService) {
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

    getLocationsByCompanyId(companyId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/companies/' + companyId + '/locations');
    }

    addLocation(location: Location, companyId: number): Observable<Location> {
        return this.http.post<Location>('api/companies/' + companyId + '/locations', location);
    }

    getPersonalLocationsByUser(user: User): Observable<Location[]> {
        return this.getPersonalLocationsByUserId(user.id);
    }

    getPersonalLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/personal-locations');
    }

    deleteLocation(locationId: number): Observable<any> {
        return this.http.delete('api/locations/' + locationId);
    }

}
