import { Component, OnInit } from '@angular/core';
import {LocationService} from "@app/_services/location.service";
import {LocationTableItem} from "@app/locations/location-table/location-table-datasource";

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.scss']
})
export class LocationsComponent implements OnInit {
  locations: LocationTableItem[];
  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationService.getLocations()
      .subscribe(locations => this.locations = locations.map(location => location.toTable()))
  }
}
