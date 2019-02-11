import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {Company} from '../../../@core/data/domain/company';
import {Location} from '../../../@core/data/domain/location';
import {ChosenLocationService} from '../chosen-location.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-location-detail',
    templateUrl: './location-detail.component.html',
    styleUrls: ['./location-detail.component.css'],
})
export class LocationDetailComponent implements OnInit {
    private refreshLocationStream$: Subject<boolean> = new Subject();
    location: Location;
    company: Company;

    editModal: Subject<boolean> = new Subject();
    deleteModal: Subject<boolean> = new Subject();
    refreshLocation: Subject<boolean> = new Subject();
    forwardToList: Subject<boolean> = new Subject();

    constructor(
        private chosenLocationService: ChosenLocationService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.getLocation();
        this.getCompany();
        this.checkRefresh();
        this.checkForward();
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

    private checkForward(): void {
        this.forwardToList.subscribe(
            () => {
                this.router.navigateByUrl('/locations');
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

    public openEditModal(): void {
        this.editModal.next(true);
    }

    public openDeleteModal(): void {
        this.deleteModal.next(true);
    }
}
