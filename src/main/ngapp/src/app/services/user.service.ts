import { Injectable } from '@angular/core';
import {User} from "app/models/user";
import {Observable, of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }


  getUsers(): Observable<User[]> {
    return of([new User(1, 'joost', '', 'Lekkerkerker', 'joostlek@outlook.com', ['SUPAUSA', "Owner"], [], [], [], '0645592066', 'Esdoornlaan', '1','3481BH', 'Harmelen', 'The Netherlands' )]);
  }
}
