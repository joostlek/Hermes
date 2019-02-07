import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
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

    getLocation(): BehaviorSubject<Location> {
        return this.location$;
    }

    pushNewLocation(location: Location): void {
        this.location$.next(location);
    }

    getCompany(): BehaviorSubject<Company> {
        return this.company$;
    }

    pushNewCompany(company: Company): void {
        this.company$.next(company);
    }
}
