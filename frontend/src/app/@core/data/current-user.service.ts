import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './domain/user';
import {filter} from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class CurrentUserService {
    private user$ = new BehaviorSubject(null);

    constructor(private http: HttpClient) {
    }

    public getCurrentUser(): Observable<User> {
        if (this.user$.getValue() === null) {
            this.updateCurrentUser();
        }
        return this.user$.pipe(
            filter((value) => value !== null),
        );
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
