import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    authenticated = false;

    constructor(
        private http: HttpClient,
    ) {
    }

    authenticate(credentials, callback, errorCallback) {
        this.http.post('api/auth/signin', credentials)
            .subscribe((response) => {
                this.authenticated = !!response['token'];
                localStorage.setItem('token', response['token']);
                return callback && callback();
            }, errorCallback);
    }
}
