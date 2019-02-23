import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
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

    editLocationModal: Subject<boolean> = new Subject();
    deleteLocationModal: Subject<boolean> = new Subject();
    refreshLocationStream: Subject<boolean> = new Subject();
    forwardToList: Subject<boolean> = new Subject();

    constructor(
        private chosenLocationService: ChosenLocationService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.getLocation();
        this.watchRefresh();
        this.checkForward();
    }

    private watchRefresh(): void {
        this.refreshLocationStream.subscribe(
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
        this.editLocationModal.next(true);
    }

    public openDeleteModal(): void {
        this.deleteLocationModal.next(true);
    }
}
