import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../@core/data/users.service';
import {User} from '../../../@core/data/domain/user';
import {LocationService} from '../../../@core/data/location.service';
import {Router} from '@angular/router';

@Component({
  selector: 'ngx-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.scss']
})
export class LocationsComponent implements OnInit {
  user: User;

  constructor(private userService: UserService,
              private locationService: LocationService,
              private router: Router) {
  }

  ngOnInit() {
    this.user = this.userService.getCurrentUser();
  }

  selectLocation(id: number) {
    this.locationService.updateCurrentLocation(id, () => {
      this.routeToDashboard();
    });
  }

  routeToDashboard(): void {
    this.router.navigateByUrl('/dashboard');
  }

  getRoleName(role: string): string {
    switch (role) {
      case 'MANAGER':
        return 'Manager';
      case 'ADVERTISER':
        return 'Advertiser';
      case 'USER':
        return 'User';
      default:
        return 'User';
    }
  }
}
