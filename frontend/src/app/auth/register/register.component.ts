import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ClrForm, ClrLoadingState} from '@clr/angular';
import {AuthService} from '../../@core/data/auth.service';
import {Registration} from '../../@core/data/domain/registration';
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
        private authService: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    public register(): void {
        this.registerButtonState = ClrLoadingState.LOADING;
        if (this.registerForm.valid) {
            const registration = new Registration(
                this.registerForm.get('firstName').value,
                this.registerForm.get('lastName').value,
                this.registerForm.get('email').value,
                this.registerForm.get('password').value);
            this.authService.register(registration)
                .subscribe(
                    () => {
                        this.registerButtonState = ClrLoadingState.SUCCESS;
                        this.router.navigateByUrl('/auth/login');
                    },
                    () => {
                        this.registerButtonState = ClrLoadingState.ERROR;
                    },
                );
        } else {
            setTimeout(() => {
                this.clrForm.markAsDirty();
                this.registerButtonState = ClrLoadingState.ERROR;
            }, 1000);
        }
    }
}
