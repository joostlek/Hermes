import {Component} from '@angular/core';

import {NbMenuItem} from '@nebular/theme';
import {UserService} from '../@core/data/users.service';
import {Location} from '../@core/data/domain/location';
import {User} from '../@core/data/domain/user';
import {LocationService} from '../@core/data/location.service';

@Component({
  selector: 'ngx-pages',
  template: `
    <ngx-sample-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-sample-layout>
  `,
})
export class PagesComponent {
  user: User;
  location: Location;
  role = '';

  constructor(private userService: UserService,
              private locationService: LocationService) {
    this.userService.onUserLogin()
      .subscribe((user: User) => {
        this.user = user;
        this.updateMenu();
      });
    this.locationService.onCurrentLocationChange()
      .subscribe((location: Location) => {
        this.location = location;
        this.updateMenu();
      });
    this.updateMenu();
  }

  menu = [];

  updateMenu(): void {
    this.getCurrentRole();
    this.menu = this.getItemList();
  }

  getCurrentRole(): void {
    if (this.location != null && this.user != null) {
      for (let i = 0; i < this.user.locations.length; i++) {
        if (this.user.locations[i]['location']['id'] === this.location.id) {
          this.role = this.user.locations[i]['role'];
        }
      }
    } else {
      this.role = '';
    }
  }

  getItemList(): NbMenuItem[] {
    const res: NbMenuItem[] = [];
    res.push(
      {
        title: 'Change locations',
        icon: 'nb-shuffle',
        link: '/pages/location',
        home: this.location == null,
      });
    if (this.location != null) {
      res.push({
        title: this.location.name,
        icon: 'nb-location',
        link: '/pages/dashboard',
        home: this.location != null,
      });
      if (this.role === 'MANAGER') {
        res.push(
          {
            title: 'Manage',
            icon: 'nb-edit',
            children: [
              {
                title: 'Images',
                link: '/pages/manage/images'
              },
              {
                title: 'Promotions',
                link: '/pages/manage/promotions'
              },
              {
                title: 'Types',
                link: '/pages/manage/types'
              },
              {
                title: 'Invoices',
                link: '/pages/manage/invoices'
              },
              {
                title: 'Advertisers',
                link: '/pages/manage/advertisers'
              },
            ],
          });
      }
      if (this.role === 'MANAGER' || this.role === 'ADVERTISER') {
        res.push(
          {
            title: 'Advertise',
            icon: 'nb-bar-chart',
            children: [
              {
                title: 'Promotions',
                link: '/pages/advertise/promotions',
              },
              {
                title: 'Images',
                link: '/pages/advertise/images',
              },
              {
                title: 'Invoices',
                link: '/pages/advertise/invoices',
              },
            ],
          }
        );
      }
    }
    if (this.user != null && this.user.role === 'SUPERUSER') {
      res.push(
        {
          title: 'Superuser',
          icon: 'nb-star',
          children: [
            {
              title: 'Users',
              link: '/pages/users',
            },
            {
              title: 'Screens',
              link: '/pages/screens',
            }
          ]
        }
      );
    }
    return res;
  }
}
