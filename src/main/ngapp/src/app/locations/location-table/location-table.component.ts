import {Component, OnInit} from '@angular/core';
import {LocationTableDataSource} from './location-table-datasource';
import {LocationService} from "@app/_services/location.service";

@Component({
  selector: 'location-table',
  templateUrl: './location-table.component.html',
  styleUrls: ['./location-table.component.css']
})
export class LocationTableComponent implements OnInit {
  dataSource: LocationTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'ownerName', 'screens', 'more'];

  constructor (private locationService: LocationService) {}
  ngOnInit() {
    this.dataSource = new LocationTableDataSource(this.locationService.getLocations());
  }
}
