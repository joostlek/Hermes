import {BehaviorSubject, Observable, of as observableOf} from 'rxjs';
import {Injectable} from '@angular/core';
import {NbAuthJWTToken, NbAuthService} from '@nebular/auth';
import {User} from './domain/user';
import {filter, share} from 'rxjs/operators';


let counter = 0;

@Injectable()
export class UserService {

  protected currentUser$: BehaviorSubject<User> = new BehaviorSubject(null);

  private users = {
    nick: {name: 'Nick Jones', picture: 'assets/images/nick.png'},
    eva: {name: 'Eva Moor', picture: 'assets/images/eva.png'},
    jack: {name: 'Jack Williams', picture: 'assets/images/jack.png'},
    lee: {name: 'Lee Wong', picture: 'assets/images/lee.png'},
    alan: {name: 'Alan Thompson', picture: 'assets/images/alan.png'},
    kate: {name: 'Kate Martinez', picture: 'assets/images/kate.png'},
  };

  private userArray: any[];

  constructor(private authService: NbAuthService) {
    this.authService.onTokenChange()
      .subscribe((token: NbAuthJWTToken) => {
        if (token.isValid()) {
          this.updateCurrentUser(token.getPayload()['sub']);
        } else {
          this.currentUser$.next(null);
        }
      });
    // this.userArray = Object.values(this.users);
  }

  getUsers(): Observable<any> {
    return observableOf(this.users);
  }

  getUserArray(): Observable<any[]> {
    return observableOf(this.userArray);
  }

  getUser(): Observable<any> {
    counter = (counter + 1) % this.userArray.length;
    return observableOf(this.userArray[counter]);
  }

  getUserById(id: number): Observable<User> {
    return observableOf(new User(1, 'Joost', undefined, 'Lekkerkerker', 'a@a.com',
      'SUPERUSER', '', [JSON.parse('{"location": {"id": 1, "name": "Cafetaria Vikas"},"role": "MANAGER"}')],
      [], [], '', '', '', '', '', ''));
  }

  updateCurrentUser(id: number): void {
    this.getUserById(id)
      .subscribe((user: User) => {
        this.pushUser(user);
      });
  }

  pushUser(user: User): void {
    this.currentUser$.next(user);
  }

  onUserLogin(): Observable<User> {
    return this.currentUser$
      .pipe(
        filter(value => !!value),
        share(),
      );
  }

  getCurrentUser(): User {
    return this.currentUser$.getValue();
  }
}
