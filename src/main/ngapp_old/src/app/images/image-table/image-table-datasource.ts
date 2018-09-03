import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {Image} from "@app/_models/image";


/**
 * Data source for the ImageTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ImageTableDataSource extends DataSource<Image> {

  constructor(private images: Observable<Image[]>) {
    super();
  }
  connect(): Observable<Image[]> {
    return this.images;
  }
  disconnect() {}
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
