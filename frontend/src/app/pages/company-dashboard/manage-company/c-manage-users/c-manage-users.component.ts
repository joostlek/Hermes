import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {filter, takeUntil} from 'rxjs/operators';
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
    private userLoadStream$: Subject<boolean> = new Subject();

    users: User[];
    addUserModal: Subject<boolean> = new Subject();
    removeUserModal: Subject<boolean> = new Subject();
    refreshUserList: Subject<boolean> = new Subject();

    userToBeDeleted: User;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private userService: UserService,
    ) {
    }

    ngOnInit() {
        this.watchRefresh();
        this.refreshUserList.next(true);
    }

    private getUsers(): void {
        this.userToBeDeleted = undefined;
        this.chosenCompanyService.getCompany()
            .pipe(
                takeUntil(this.userLoadStream$),
            )
            .subscribe(
                (company: Company) => {
                    this.userService.getUsersByCompanyId(company.id)
                        .pipe(
                            takeUntil(this.userLoadStream$),
                        )
                        .subscribe(
                            (users) => {
                                this.users = users;
                            },
                        );
                },
            );
    }

    private watchRefresh(): void {
        this.refreshUserList
            .pipe(
                filter((value) => value !== false),
            )
            .subscribe(
                () => {
                    this.userLoadStream$.next(true);
                    this.getUsers();
                },
            );
    }

    public openAddUserModal(): void {
        this.addUserModal.next(true);
    }

    public openRemoveUserModal(user: User): void {
        this.userToBeDeleted = user;
        this.removeUserModal.next(true);
    }
}
