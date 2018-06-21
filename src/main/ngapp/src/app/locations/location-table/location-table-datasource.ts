import { DataSource } from '@angular/cdk/collections';
import { Observable} from 'rxjs';
import { Location } from '@app/_models/location';

/**
 * Data source for the LocationTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class LocationTableDataSource extends DataSource<Location> {

  constructor(private locations: Observable<Location[]>) {
    super();
  }
  connect(): Observable<Location[]> {
    return this.locations;
  }
  disconnect() {}
}
