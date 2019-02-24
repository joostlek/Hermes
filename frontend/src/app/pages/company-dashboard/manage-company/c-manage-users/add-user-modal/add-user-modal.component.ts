import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../../../@core/data/company.service';
import {Company} from '../../../../../@core/data/domain/company';
import {ChosenCompanyService} from '../../../chosen-company.service';

@Component({
    selector: 'app-add-user-modal',
    templateUrl: './add-user-modal.component.html',
    styleUrls: ['./add-user-modal.component.css'],
})
export class AddUserModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() refreshStream: Subject<boolean>;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    open = false;
    error: string;
    company: Company;

    email = new FormGroup({
        email: new FormControl('', [Validators.email, Validators.required]),
    });

    constructor(
        private companyService: CompanyService,
        private chosenCompanyService: ChosenCompanyService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
        this.getCompany();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value: boolean) => {
                this.open = value;
            },
        );
    }

    public closeModal(): void {
        this.email.reset();
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

    public addUserToCompany(): void {
        if (this.email.valid) {
            this.submitButtonState = ClrLoadingState.LOADING;
            this.companyService.addUserToCompany(this.email.value['email'], this.company.id)
                .subscribe(() => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                    this.refreshStream.next(true);
                        this.closeModal();
                    }, (error) => {
                        this.submitButtonState = ClrLoadingState.ERROR;
                        this.error = JSON.parse(error.error)['message'];
                        console.error(this.error);
                    },
                );
        }
    }

    public getErrorMessage(error: ValidationErrors): string {
        if (error === null) {
            return '';
        }
        if (error['email'] === true) {
            return 'Please enter a valid email address';
        }
        if (error['required'] === true) {
            return 'Please fill in an email address';
        }
    }

}
