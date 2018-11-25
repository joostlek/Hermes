import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../@core/data/domain/user';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.css'],
})
export class UserTableComponent implements OnInit {
    @Input('users') users: User[];

    constructor() {
    }

    ngOnInit() {
    }

}
