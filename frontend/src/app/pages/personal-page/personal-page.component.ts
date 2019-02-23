import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {CurrentUserService} from '../../@core/data/current-user.service';
import {User} from '../../@core/data/domain/user';
import {calculateDifference, Interval} from '../../@core/util/interval';

@Component({
    selector: 'app-personal-page',
    templateUrl: './personal-page.component.html',
    styleUrls: ['./personal-page.component.css'],
})
export class PersonalPageComponent implements OnInit {
    user: User;
    intervalCreated: Interval;
    intervalUpdated: Interval;
    showUpdated = false;

    editUserModal: Subject<boolean> = new Subject<boolean>();

    constructor(
        private currentUserService: CurrentUserService,
    ) {
    }

    ngOnInit() {
        this.getCurrentUser();
    }

    public openEditModal(): void {
        this.editUserModal.next(true);
    }

    getCurrentUser(): void {
        this.currentUserService.getCurrentUser()
            .subscribe((user: User) => {
                    this.user = user;
                    this.intervalCreated = calculateDifference(new Date(), user.created);
                    this.intervalUpdated = calculateDifference(new Date(), user.updated);
                    if (this.user.created !== this.user.updated) {
                        this.showUpdated = true;
                    }
                },
            );
    }

}
