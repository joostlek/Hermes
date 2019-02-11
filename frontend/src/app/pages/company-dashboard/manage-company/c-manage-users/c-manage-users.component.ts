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
    addUserModalOpen: Subject<boolean> = new Subject();
    removeUserModalOpen: Subject<boolean> = new Subject();
    refreshUserList: Subject<boolean> = new Subject();

    userToBeDeleted: User;

    constructor(
        private chosenCompanyService: ChosenCompanyService,
        private userService: UserService,
    ) {
    }

    ngOnInit() {
        this.getUsers();
        this.checkModalOpen();
    }

    getUsers(): void {
        this.chosenCompanyService.getCompany()
            .pipe(
                takeUntil(this.userLoadStream$),
            )
            .subscribe((company: Company) => {
                    this.userService.getUsersByCompanyId(company.id)
                        .pipe(
                            takeUntil(this.userLoadStream$),
                        )
                        .subscribe((users) => {
                                this.users = users;
                            },
                        );
                },
            );
    }

    openModal(): void {
        this.addUserModalOpen.next(true);
    }

    private checkModalOpen(): void {
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

    private onDelete(user: User): void {
        this.userToBeDeleted = user;
        this.removeUserModalOpen.next(true);
    }
}
