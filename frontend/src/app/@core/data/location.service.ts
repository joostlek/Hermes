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

    deleteLocation(locationId: number): Observable<any> {
        return this.http.delete('api/locations/' + locationId);
    }

    updateLocation(location: Location): Observable<Location> {
        return this.http.put<Location>('api/locations/' + location.id, location);
    }

    public getAdvertisingLocationsByCompanyId(companyId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/companies/' + companyId + '/advertising');
    }

    public getAllLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/locations');
    }

    public getAdvertisingLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/locations/advertising');
    }

    public getPersonalLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/locations/personal');
    }

    public addAdvertisingLocationToCompany(locationId: number, companyId: number): Observable<any> {
        return this.http.post('api/companies/' + companyId + '/advertising', {locationId});
    }

    public removeAdvertisingCompanyFromLocation(locationId: number, companyId: number): Observable<Location> {
        return this.http.delete<Location>('api/locations/' + locationId + '/advertising/' + companyId);
    }

}
