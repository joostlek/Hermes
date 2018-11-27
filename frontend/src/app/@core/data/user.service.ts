import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from './domain/user';

@Injectable({
    providedIn: 'root',
})
export class UserService {

    constructor(private http: HttpClient) {
    }

    getUsers(): Observable<User[]> {
        return this.http.get<User[]>('api/users');
    }

    getUser(id: number): Observable<User> {
        return this.http.get<User>('api/users/' + id);
    }
}
