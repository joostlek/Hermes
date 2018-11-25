import {Component, OnInit} from '@angular/core';
import {UserService} from '../../@core/data/user.service';
import {User} from '../../@core/data/domain/user';

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {

    constructor(
        private userService: UserService,
    ) {
    }

    users: User[] = [];

    ngOnInit() {
        this.getUsers();
    }

    getUsers(): void {
        this.userService.getUsers().subscribe((users) => this.users = users);
    }

}
