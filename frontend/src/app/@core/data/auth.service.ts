import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {tap} from 'rxjs/operators';
import {Registration} from './domain/registration';
import {TokenService} from './token.service';
import {CurrentUserService} from './current-user.service';
import {Observable} from 'rxjs';
import {User} from './domain/user';
import {MessageService} from './message.service';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    authenticated = false;

    constructor(
        private messageService: MessageService,
        private http: HttpClient,
        private token: TokenService,
        private currentUserService: CurrentUserService,
    ) {
    }

    public authenticate(credentials): Observable<User> {
        this.messageService.log('Authentication attempt');
        return this.http.get('api/users/me', {
                headers: {
                    'Authorization': 'Basic ' + btoa(credentials['username'] + ':' + credentials['password']),
                    'X-Requested-With': 'XMLHttpRequest',
                },
            },
        )
            .pipe(
                tap((user: User) => {
                        this.currentUserService.updateCurrentUser(user);
                        this.authenticated = true;
                    },
                ),
            );
    }

    public logout(): Observable<any> {
        this.messageService.log('User logged out');
        return this.http.get('api/logout')
            .pipe(
                tap(() => {
                        console.log('log out');
                        this.authenticated = false;
                    },
                ),
            );
    }

    public register(registration: Registration): Observable<any> {
        this.messageService.log('Register attempt');
        return this.http.post('api/users', registration);
    }
}
