import {Component, Input, OnInit} from '@angular/core';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../../../@core/data/company.service';
import {Company} from '../../../../../@core/data/domain/company';
import {ChosenCompanyService} from '../../../chosen-company.service';
import {User} from '../../../../../@core/data/domain/user';

@Component({
    selector: 'app-remove-user-modal',
    templateUrl: './remove-user-modal.component.html',
    styleUrls: ['./remove-user-modal.component.css'],
})
export class RemoveUserModalComponent implements OnInit {
    @Input('open') openStream: Subject<boolean>;
    @Input('refreshUserList') refreshList: Subject<boolean>;
    @Input('user') user: User;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;
    error: string;
    company: Company;

    constructor(
        private companyService: CompanyService,
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.checkOpenStream();
        this.getCompany();
    }

    private checkOpenStream(): void {
        this.openStream.subscribe(
            (value) => {
                this.open = value;
            },
        );
    }

    public closeModal(): void {
        this.error = undefined;
        this.openStream.next(false);
    }

    private getCompany(): void {
        this.chosenCompanyService.getCompany()
            .subscribe(
                (company) => {
                    this.company = company;
                },
            );
    }

    public removeUser(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.companyService.removeUserFromCompany(this.user.id, this.company.id)
            .subscribe(
                () => {
                    this.submitButtonState = ClrLoadingState.SUCCESS;
                    this.refreshList.next(true);
                    this.closeModal();
                },
                (error) => {
                    this.error = JSON.parse(error.error)['message'];
                    this.submitButtonState = ClrLoadingState.ERROR;
                    console.error(this.error);
                },
            );
    }

}