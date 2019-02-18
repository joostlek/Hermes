import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
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
        this.checkRefresh();
        this.checkForward();
    }

    private checkRefresh(): void {
        this.refreshLocation.subscribe(
            () => {
                this.refreshLocationStream$.next(true);
                this.getLocation();
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

    public openEditModal(): void {
        this.editModal.next(true);
    }

    public openDeleteModal(): void {
        this.deleteModal.next(true);
    }
}
