import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {Company} from '../../@core/data/domain/company';
import {Location} from '../../@core/data/domain/location';

@Injectable({
    providedIn: 'root',
})
export class ChosenLocationService {
    private location$ = new BehaviorSubject(null);
    private company$ = new BehaviorSubject(null);

    constructor() {
    }

    public getLocation(): Observable<Location> {
        return this.location$
            .pipe(
                filter((value) => value !== null),
            );
    }

    public pushNewLocation(location: Location): void {
        this.location$.next(location);
    }

    public getCompany(): Observable<Company> {
        return this.company$
            .pipe(
                filter((value) => value !== null),
            );
    }

    public pushNewCompany(company: Company): void {
        this.company$.next(company);
    }
}
