import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Company} from '../../@core/data/domain/company';

@Injectable({
    providedIn: 'root',
})
export class ChosenCompanyService {
    private company$ = new BehaviorSubject(null);

    constructor() {
    }

    getCompany(): BehaviorSubject<Company> {
        return this.company$;
    }

    pushNewCompany(company: Company): void {
        this.company$.next(company);
    }
}
