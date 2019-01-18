import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {Registration} from './domain/registration';
import {TokenService} from './token.service';
import {CurrentUserService} from './current-user.service';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    authenticated = false;

    constructor(
        private http: HttpClient,
        private token: TokenService,
        private currentUserService: CurrentUserService,
    ) {
    }

    authenticate(credentials, callback, errorCallback) {
        this.token.removeToken();
        this.http.post('api/auth/user/signin', credentials)
            .subscribe((response) => {
                this.authenticated = !!response['token'];
                this.token.storeToken(response['token']);
                this.currentUserService.updateCurrentUser();
                return callback && callback();
            }, errorCallback);
    }

    refresh() {
        return this.http.get('api/auth/user/refresh')
            .pipe(map((response) => {
                this.authenticated = !!response['token'];
                this.token.storeToken(response['token']);
            }));
    }

    logout(): void {
        this.token.removeToken();
        this.currentUserService.removeCurrentUser();
        this.authenticated = false;
    }

    register(registration: Registration, callback, errorCallback): void {
        this.token.removeToken();
        this.http.post('api/auth/register', registration)
            .subscribe((response) => {
                if (response.hasOwnProperty('id')) {
                    return callback && callback();
                }
            }, errorCallback);
    }
}
