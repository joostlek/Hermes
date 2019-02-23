import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../@core/data/auth.service';

@Component({
    selector: 'app-logout',
    templateUrl: './logout.component.html',
    styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

    constructor(
        private authService: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.logout();

    }

    private logout(): void {
        this.authService.logout()
            .subscribe(
                () => {
                    this.router.navigateByUrl('/auth/login');
                },
            );
    }

}
