import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {CurrentUserService} from '../data/current-user.service';
import {User} from '../data/domain/user';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        private currentUserService: CurrentUserService,
        private router: Router,
    ) {
    }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        return this.currentUserService.getCurrentUser()
            .pipe(
                map(
                    (user: User, index: number) => {
                        if (user) {
                            return true;
                        }
                        this.router.navigate(['auth/login']);
                        return false;
                    },
                ),
            );
    }
}
