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
    modalOpen: Subject<boolean> = new Subject();

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
                filter((value) => value !== null),
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
        this.modalOpen.next(true);
    }

    private checkModalOpen(): void {
        this.modalOpen
            .pipe(
                filter((value) => value !== true),
            )
            .subscribe(
                () => {
                    this.userLoadStream$.next(true);
                    this.getUsers();
                },
            );
    }
}
