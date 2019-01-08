import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
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
        this.http.post('api/auth/signin', credentials)
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
}
