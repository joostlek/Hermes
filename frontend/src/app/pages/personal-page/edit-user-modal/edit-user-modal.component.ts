import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrLoadingState} from '@clr/angular';
import {Subject} from 'rxjs';
import {CurrentUserService} from '../../../@core/data/current-user.service';
import {User} from '../../../@core/data/domain/user';
import {UserService} from '../../../@core/data/user.service';

@Component({
    selector: 'app-edit-user-modal',
    templateUrl: './edit-user-modal.component.html',
    styleUrls: ['./edit-user-modal.component.css'],
})
export class EditUserModalComponent implements OnInit {
    @Input() openStream: Subject<boolean>;
    @Input() user: User;
    submitButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    modalOpen = false;

    error: string;

    registerForm = new FormGroup({
            firstName: new FormControl('', [Validators.required]),
            lastName: new FormControl('', [Validators.required]),
            email: new FormControl('', {validators: [Validators.required, Validators.email], updateOn: 'blur'}),
            // password: new FormControl('', [Validators.required, Validators.minLength(8)]),
            // repeatPassword: new FormControl('', [Validators.required]),
        },
        // {validators: repeatPasswordValidator}
    );

    constructor(
        private userService: UserService,
        private currentUserService: CurrentUserService,
    ) {
    }

    ngOnInit() {
        this.watchOpen();
    }

    private watchOpen(): void {
        this.openStream.subscribe(
            (value: boolean) => {
                this.modalOpen = value;
                if (value === true) {
                    this.fillForms();
                }
            },
        );
    }

    private fillForms(): void {
        this.registerForm.controls['firstName'].setValue(this.user.firstName);
        this.registerForm.controls['lastName'].setValue(this.user.lastName);
        this.registerForm.controls['email'].setValue(this.user.email);
    }

    public closeModal(): void {
        this.registerForm.reset();
        this.openStream.next(false);
    }

    public getErrorCount(formGroup: FormGroup): number {
        let res = 0;
        for (const key in formGroup.controls) {
            if (formGroup.controls.hasOwnProperty(key)) {
                const value = formGroup.controls[key];
                if (value.errors !== null) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public updateUser(): void {
        this.submitButtonState = ClrLoadingState.LOADING;
        if (!this.registerForm.invalid) {
            this.user.firstName = this.registerForm.value['firstName'];
            this.user.lastName = this.registerForm.value['lastName'];
            this.user.email = this.registerForm.value['email'];
            this.userService.updateUser(this.user)
                .subscribe(
                    () => {
                        this.submitButtonState = ClrLoadingState.SUCCESS;
                        this.currentUserService.refreshCurrentUser();
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

    public isDisabled(): boolean {
        return this.registerForm.invalid;
    }

}
