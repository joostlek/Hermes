import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {Company} from '../../@core/data/domain/company';

@Injectable({
    providedIn: 'root',
})
export class ChosenCompanyService {
    private company$ = new BehaviorSubject(null);

    constructor() {
    }

    getCompany(): Observable<Company> {
        return this.company$
            .pipe(
                filter((value) => value !== null),
            );
    }

    pushNewCompany(company: Company): void {
        this.company$.next(company);
    }
}
