import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {CurrentUserService} from '../data/current-user.service';
import {User} from '../data/domain/user';

@Injectable()
export class RoleGuardService implements CanActivate {

    constructor(
        private currentUserService: CurrentUserService,
        private router: Router,
    ) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        const expectedRole = route.data.expectedRole;
        return this.currentUserService.getCurrentUser()
            .pipe(
                map(
                    (user: User, index: number) => {
                        if (user.roles.indexOf(expectedRole) > -1) {
                            return true;
                        }
                        this.router.navigate(['/']);
                        return false;
                    },
                ),
            );
    }
}
