import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClrForm, ClrLoadingState} from '@clr/angular';
import {AuthService} from '../../@core/data/auth.service';
import {Registration} from '../../@core/data/domain/registration';
import {Router} from '@angular/router';
import {repeatPasswordValidator} from '../../@core/directives/repeat-password.directive';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
    @ViewChild(ClrForm) clrForm;

    registerButtonState: ClrLoadingState = ClrLoadingState.DEFAULT;

    registerForm = new FormGroup({
            firstName: new FormControl('', [Validators.required]),
            lastName: new FormControl('', [Validators.required]),
            email: new FormControl('', {validators: [Validators.required, Validators.email], updateOn: 'blur'}),
            password: new FormControl('', [Validators.required, Validators.minLength(8)]),
            repeatPassword: new FormControl('', [Validators.required]),
        },
        {validators: repeatPasswordValidator});

    constructor(
        private auth: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    get firstName() {
        return this.registerForm.get('firstName');
    }

    get lastName() {
        return this.registerForm.get('lastName');
    }

    get email() {
        return this.registerForm.get('email');
    }

    get password() {
        return this.registerForm.get('password');
    }

    get repeatPassword() {
        return this.registerForm.get('repeatPassword');
    }

    register() {
        this.registerButtonState = ClrLoadingState.LOADING;
        if (this.registerForm.valid) {
            const registration = new Registration(this.firstName.value, this.lastName.value, this.email.value, this.password.value);
            this.auth.register(registration, () => {
                this.registerButtonState = ClrLoadingState.SUCCESS;
                this.router.navigateByUrl('/auth/login');
            }, () => {
                this.registerButtonState = ClrLoadingState.ERROR;
            });
        } else {
            setTimeout(() => {
                this.clrForm.markAsDirty();
                this.registerButtonState = ClrLoadingState.ERROR;
            }, 1000);
        }
    }
}
