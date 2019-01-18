import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Registration} from './domain/registration';
import {TokenService} from './token.service';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    authenticated = false;

    constructor(
        private http: HttpClient,
        private token: TokenService,
    ) {
    }

    authenticate(credentials, callback, errorCallback) {
        this.token.removeToken();
        this.http.post('api/auth/user/signin', credentials)
            .subscribe((response) => {
                this.authenticated = !!response['token'];
                this.token.storeToken(response['token']);
                return callback && callback();
            }, errorCallback);
    }

    logout(): void {
        this.token.removeToken();
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
