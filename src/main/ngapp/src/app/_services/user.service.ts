import { Injectable } from '@angular/core';
import {User} from "app/_models/user";
import {Observable, of} from "rxjs/index";
import {Roles} from "@app/_models/roles";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }


  getUsers(): Observable<User[]> {
    return of([new User(1, 'joost', '', 'Lekkerkerker', 'joostlek@outlook.com', Roles.ROLE_SUPERUSER, [], [], [], '0645592066', 'Esdoornlaan', '1','3481BH', 'Harmelen', 'The Netherlands' )]);
  }
}
