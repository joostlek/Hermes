import {Component, OnInit, ViewChild} from '@angular/core';
import {ClrWizard} from '@clr/angular';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {
    @ViewChild('wizardlg') wizardLarge: ClrWizard;

    users: Object[] = [
        {
            firstName: 'Joost',
            lastName: 'Lekkerkerker',
            id: 1,
            email: 'joostlek@outlook.com'
        },
        {
            firstName: 'Joost',
            lastName: 'Lekkerkerker',
            id: 1,
            email: 'joostlek@outlook.com'
        },
    ];

    constructor() {
    }

    ngOnInit() {
    }

}
