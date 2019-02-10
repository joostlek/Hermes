import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {EMPTY, Observable} from 'rxjs';
import {switchMap} from 'rxjs/operators';
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

    addLocation(location: Location): Observable<Location> {
        return this.currentUserService.getCurrentUser()
            .pipe(
                switchMap((value) => {
                        if (value !== null) {
                            return this.addLocationWithUserId(location, value.id);
                        } else {
                            return EMPTY;
                        }
                    },
                ),
            );
    }

    private addLocationWithUserId(location: Location, userId: number): Observable<Location> {
        return this.http.post<Location>('api/users/' + userId + '/locations', location);
    }

    getPersonalLocationsByUser(user: User): Observable<Location[]> {
        return this.getPersonalLocationsByUserId(user.id);
    }

    getPersonalLocationsByUserId(userId: number): Observable<Location[]> {
        return this.http.get<Location[]>('api/users/' + userId + '/personal-locations');
    }
}
