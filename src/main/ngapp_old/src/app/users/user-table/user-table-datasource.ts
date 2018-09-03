import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {User} from "@app/_models/user";


export class UserTableDataSource extends DataSource<User> {

  constructor(private observable: Observable<User[]>) {
    super();
  }

  connect(): Observable<User[]> {
    return this.observable;
  }

  disconnect() {}

}
