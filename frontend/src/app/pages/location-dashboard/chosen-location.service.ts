import {Injectable} from '@angular/core';
import {Company} from '../../@core/data/domain/company';
import {Location} from '../../@core/data/domain/location';

@Injectable({
    providedIn: 'root',
})
export class ChosenLocationService {
    location: Location;
    company: Company;

    constructor() {
    }
}
