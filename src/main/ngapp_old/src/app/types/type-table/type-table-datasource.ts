import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {Type} from "@app/_models/type";


export class TypeTableDataSource extends DataSource<Type> {

  constructor(private observable: Observable<Type[]>) {
    super();
  }
  connect(): Observable<Type[]> {
    return this.observable;
  }
  disconnect() {}
}
