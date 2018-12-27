import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../@core/data/auth.service';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    credentials = {};
    errorMessage: string;

    constructor(
        private auth: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    authenticate() {
        localStorage.removeItem('token');
        this.auth.authenticate(this.credentials, () => {
            this.router.navigateByUrl('/');
        }, (error: HttpErrorResponse) => {
            this.errorMessage = error.error['message'];
        });

    }
}
