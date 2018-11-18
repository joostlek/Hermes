import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.css'],
})
export class UserTableComponent implements OnInit {

    users: object[] = [
        {
            firstName: 'Joost',
            lastName: 'Lekkerkerker',
            id: 1,
            email: 'joostlek@outlook.com',
        },
        {
            firstName: 'Joost',
            lastName: 'Lekkerkerker',
            id: 1,
            email: 'joostlek@outlook.com',
        },
    ];

    constructor() {
    }

    ngOnInit() {
    }

}
