import {Component, OnInit} from '@angular/core';
import {LocationTableDataSource} from './location-table-datasource';
import {LocationService} from "@app/_services/location.service";
import {User} from "@app/_models/user";
import {Roles} from "@app/_models/roles";

@Component({
  selector: 'location-table',
  templateUrl: './location-table.component.html',
  styleUrls: ['./location-table.component.css']
})
export class LocationTableComponent implements OnInit {
  dataSource: LocationTableDataSource;
  user: User;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'ownerName', 'screens', 'more'];

  constructor (private locationService: LocationService) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }
  ngOnInit() {
    if (this.user.role === Roles.ROLE_SUPERUSER) {
      this.dataSource = new LocationTableDataSource(this.locationService.getLocations());
    } else {
      this.dataSource = new LocationTableDataSource(this.locationService.getMyLocations());
    }
  }
}
