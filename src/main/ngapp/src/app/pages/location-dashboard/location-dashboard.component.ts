import { Component, OnInit } from '@angular/core';
import { ActionsComponent} from './actions/actions.component';
import {LocationService} from '../../@core/data/location.service';


@Component({
  selector: 'ngx-location-dashboard',
  templateUrl: './location-dashboard.component.html',
  styleUrls: ['./location-dashboard.component.scss'],
})
export class LocationDashboardComponent implements OnInit {

  constructor(private locationService: LocationService) {
    this.locationService.pushCurrentLocation(null);
  }

  ngOnInit() {
  }

}
