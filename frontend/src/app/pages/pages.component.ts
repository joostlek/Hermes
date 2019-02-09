import {Component, OnInit} from '@angular/core';
import {CurrentUserService} from '../@core/data/current-user.service';
import {User} from '../@core/data/domain/user';

@Component({
    selector: 'app-pages',
    templateUrl: './pages.component.html',
    styleUrls: ['./pages.component.css'],
    host: {
        '[class.content-container]': 'true',
    },
})
export class PagesComponent implements OnInit {
    user: User;

    constructor(private currentUserService: CurrentUserService) {
    }

    ngOnInit() {
        this.getCurrentUser();
    }

    getCurrentUser() {
        this.currentUserService.getCurrentUser()
            .subscribe((user: User) => {
                this.user = user;
            });
    }

}
