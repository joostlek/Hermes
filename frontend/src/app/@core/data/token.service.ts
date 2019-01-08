import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class TokenService {

    constructor() {
    }

    storeToken(token: string): void {
        localStorage.setItem('token', token);
    }

    getToken(): string {
        return localStorage.getItem('token');
    }

    removeToken(): void {
        return localStorage.removeItem('token');
    }
}
