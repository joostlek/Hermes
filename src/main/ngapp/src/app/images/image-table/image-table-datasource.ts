import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {ImageService} from "../shared/image.service";
import {Image} from "../shared/image.model";

// TODO: Replace this with your own data model type
export interface ImageTableItem {
  id: number;
  name: string;
  promotion_name: string;
  promotion_id: number;
  user_name: string;
  user_id: number;
  screens: object[];
  width: number;
  height: number;
  active: boolean;
}

/**
 * Data source for the ImageTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ImageTableDataSource extends DataSource<ImageTableItem> {
  data: ImageTableItem[] = [];

  constructor(private paginator: MatPaginator, private sort: MatSort, private images: ImageTableItem[]) {
    super();
    this.data = images;
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<ImageTableItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    // Set the paginators length
    this.paginator.length = this.data.length;

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: ImageTableItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: ImageTableItem[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'id': return compare(+a.id, +b.id, isAsc);
        case 'name': return compare(a.name, b.name, isAsc);
        case 'promotion_name': return compare(a.promotion_name, b.promotion_name, isAsc);
        case 'promotion_id': return compare(+a.promotion_id, +b.promotion_id, isAsc);
        case 'user_name': return compare(a.user_name, b.user_name, isAsc);
        case 'user_id': return compare(+a.user_id, +b.user_id, isAsc);
        case 'width': return compare(+a.width, +b.width, isAsc);
        case 'height': return compare(+a.height, +b.height, isAsc);
        case 'active': return compare(+a.active, +b.active, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
