import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../@core/data/auth.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-logout',
    templateUrl: './logout.component.html',
    styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

    constructor(
        private auth: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.logout();

    }

    logout(): void {
        this.auth.logout();
        this.router.navigateByUrl('/auth/login');
    }

}
