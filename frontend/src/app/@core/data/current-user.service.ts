import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from './domain/user';

@Injectable({
    providedIn: 'root'
})
export class CurrentUserService {
    private user$ = new BehaviorSubject(null);

    constructor(private http: HttpClient) {
    }

    getCurrentUser() {
        console.log(this.user$.getValue() === null);
        if (this.user$.getValue() === null) {
            this.updateCurrentUser();
        }
        return this.user$;
    }

    updateCurrentUser(): void {
        this.http.get<User>('api/users/me')
            .subscribe((user) => {
                this.user$.next(user);
            });
    }

    removeCurrentUser() {
        this.user$.next(null);
    }
}