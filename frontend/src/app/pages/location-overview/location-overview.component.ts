import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {CompanyService} from '../../@core/data/company.service';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {Location} from '../../@core/data/domain/location';
import {LocationService} from '../../@core/data/location.service';

@Component({
    selector: 'app-location-overview',
    templateUrl: './location-overview.component.html',
    styleUrls: ['./location-overview.component.css'],
})
export class LocationOverviewComponent implements OnInit {
    allLocationStream: BehaviorSubject<Location[]> = new BehaviorSubject(null);
    personalLocationStream: BehaviorSubject<Location[]> = new BehaviorSubject(null);
    advertisingLocationStream: BehaviorSubject<Location[]> = new BehaviorSubject(null);

    constructor(
        private companyService: CompanyService,
        private currentUserService: CurrentUserService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.initializeStreams();
        this.removeSelectedCompany();
    }

    private removeSelectedCompany(): void {
        sessionStorage.removeItem('selectedCompany');
    }

    private initializeStreams(): void {
        this.currentUserService.getCurrentUser()
            .subscribe(
                (user) => {
                    this.locationService.getAllLocationsByUserId(user.id)
                        .subscribe(
                            (locations: Location[]) => {
                                this.allLocationStream.next(locations);
                            },
                        );
                    this.locationService.getAdvertisingLocationsByUserId(user.id)
                        .subscribe(
                            (locations: Location[]) => {
                                this.advertisingLocationStream.next(locations);
                            },
                        );
                    this.locationService.getPersonalLocationsByUserId(user.id)
                        .subscribe(
                            (locations: Location[]) => {
                                this.personalLocationStream.next(locations);
                            },
                        );
                },
            );
    }

    public getAllLocationsStream(): Observable<Location[]> {
        return this.allLocationStream.pipe(
            filter((value) => value !== null),
        );
    }

    public getAdvertisingLocationsStream(): Observable<Location[]> {
        return this.advertisingLocationStream.pipe(
            filter((value) => value !== null),
        );
    }

    public getPersonalLocationsStream(): Observable<Location[]> {
        return this.personalLocationStream.pipe(
            filter((value) => value !== null),
        );
    }

}
