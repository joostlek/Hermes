import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {User} from './domain/user';

@Injectable({
    providedIn: 'root',
})
export class CurrentUserService {
    private user$: BehaviorSubject<User> = new BehaviorSubject<User>(null);

    constructor(private http: HttpClient) {
    }

    public getCurrentUser(): Observable<User> {
        return this.user$.pipe(
            filter((value) => value !== null),
        );
    }

    public updateCurrentUser(user: User): void {
        this.user$.next(user);
    }

    public refreshCurrentUser(): void {
        this.http.get('api/users/me')
            .subscribe(
                (user: User) => {
                    this.updateCurrentUser(user);
                },
            );
    }

    public removeCurrentUser(): void {
        this.user$.next(null);
    }
}
