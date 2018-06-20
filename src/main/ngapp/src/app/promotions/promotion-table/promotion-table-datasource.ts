import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {Promotion} from "@app/_models/promotion";
import {PromotionService} from "@app/_services/promotion.service";

// TODO: Replace this with your own data model type
export interface PromotionTableItem {
  name: string;
  id: number;
  typeId: number;
  typeName: string;
  userId: number;
  userName: string;
  images: number;
  locationId: number;
  locationName: string;
  startDate: string;
}

/**
 * Data source for the PromotionTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class PromotionTableDataSource extends DataSource<Promotion> {

  constructor(private observable: Observable<Promotion[]>) {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<Promotion[]> {
    return this.observable;
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.

  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}
}
