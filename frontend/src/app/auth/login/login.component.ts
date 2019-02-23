import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../@core/data/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    credentials = {
        username: '',
        password: '',
    };

    error: string;

    constructor(
        private authService: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    public authenticate(): void {
        this.authService.authenticate(this.credentials)
            .subscribe(
                () => {
                    this.router.navigateByUrl('/');
                },
                () => {
                    this.error = 'Credentials incorrect';
                },
            );
    }
}
