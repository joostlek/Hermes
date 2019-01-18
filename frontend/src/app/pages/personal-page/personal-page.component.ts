import {Component, OnInit} from '@angular/core';
import {User} from '../../@core/data/domain/user';
import {CurrentUserService} from '../../@core/data/current-user.service';
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

    constructor(
        private currentUserService: CurrentUserService,
    ) {
    }

    ngOnInit() {
        this.getCurrentUser();
    }

    getCurrentUser(): void {
        this.currentUserService.getCurrentUser()
            .subscribe((user: User) => {
                this.user = user;
                if (user !== null) {
                    this.intervalCreated = calculateDifference(new Date(), user.created);
                    this.intervalUpdated = calculateDifference(new Date(), user.updated);
                    if (this.user.created === this.user.updated) {
                        this.showUpdated = true;
                    }
                }
            });
    }

}
