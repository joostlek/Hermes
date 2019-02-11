import {Component, Input, OnInit} from '@angular/core';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CompanyService} from '../../../@core/data/company.service';
import {Company} from '../../../@core/data/domain/company';
import {ChosenCompanyService} from '../chosen-company.service';

@Component({
    selector: 'app-remove-company-modal',
    templateUrl: './remove-company-modal.component.html',
    styleUrls: ['./remove-company-modal.component.css'],
})
export class RemoveCompanyModalComponent implements OnInit {
    @Input('open') openStream: Subject<boolean>;
    @Input('refreshLocation') refreshList: Subject<boolean>;
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

    public removeCompany(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        this.companyService.deleteCompany(this.company.id)
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
