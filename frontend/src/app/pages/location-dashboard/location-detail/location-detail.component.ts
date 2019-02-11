import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {Company} from '../../../@core/data/domain/company';
import {Location} from '../../../@core/data/domain/location';
import {ChosenLocationService} from '../chosen-location.service';

@Component({
    selector: 'app-location-detail',
    templateUrl: './location-detail.component.html',
    styleUrls: ['./location-detail.component.css'],
})
export class LocationDetailComponent implements OnInit {
    private refreshLocationStream$: Subject<boolean> = new Subject();
    location: Location;
    company: Company;

    openEditModal: Subject<boolean> = new Subject();
    refreshLocation: Subject<boolean> = new Subject();

    constructor(
        private chosenLocationService: ChosenLocationService,
    ) {
    }

    ngOnInit() {
        this.getLocation();
        this.getCompany();
        this.checkRefresh();
    }

    private checkRefresh(): void {
        this.refreshLocation.subscribe(
            () => {
                this.refreshLocationStream$.next(true);
                this.getLocation();
                this.getCompany();
            },
        );
    }

    private getLocation(): void {
        this.chosenLocationService.getLocation()
            .pipe(
                takeUntil(this.refreshLocationStream$),
            )
            .subscribe((location) => {
                    this.location = location;
                },
            );
    }

    private getCompany(): void {
        this.chosenLocationService.getCompany()
            .pipe(
                takeUntil(this.refreshLocationStream$),
            )
            .subscribe((company) => {
                    this.company = company;
                },
            );
    }

    public openModal(): void {
        this.openEditModal.next(true);
    }
}
