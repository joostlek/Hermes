import {Component, OnInit} from '@angular/core';
import {filter} from 'rxjs/operators';
import {Company} from '../../../../@core/data/domain/company';
import {User} from '../../../../@core/data/domain/user';
import {UserService} from '../../../../@core/data/user.service';
import {ChosenCompanyService} from '../../chosen-company.service';

@Component({
    selector: 'app-c-manage-users',
    templateUrl: './c-manage-users.component.html',
    styleUrls: ['./c-manage-users.component.css'],
})
export class CManageUsersComponent implements OnInit {
    users: User[];

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private userService: UserService,
    ) {
    }

    ngOnInit() {
        this.getUsers();
    }

    getUsers(): void {
        this.chosenCompanyService.getCompany()
            .pipe(
                filter((value) => value !== null),
            )
            .subscribe((company: Company) => {
                    this.userService.getUsersByCompanyId(company.id)
                        .subscribe((users) => {
                                this.users = users;
                            },
                        );
                },
            );
    }
}
