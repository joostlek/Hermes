import { DataSource } from '@angular/cdk/collections';
import { Observable} from 'rxjs';
import {Screen} from "@app/_models/screen";


export class ScreenTableDataSource extends DataSource<Screen> {

  constructor(private observable: Observable<Screen[]>) {
    super();
  }

  connect(): Observable<Screen[]> {
    return this.observable;
  }


  disconnect() {}
}
