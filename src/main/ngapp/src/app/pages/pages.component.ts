import {Component} from '@angular/core';

import {NbMenuItem} from '@nebular/theme';
import {UserService} from '../@core/data/users.service';
import {Location} from '../@core/data/domain/location';
import {NbAuthJWTToken, NbAuthService} from "@nebular/auth";

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
  user: any;
  token: string;

  constructor(private userService: UserService,
              private authService: NbAuthService) {
    this.userService.getUsers()
      .subscribe((users: any) => {
        this.user = users.nick;
        this.menu = this.getItemList();
      });
    this.authService.onTokenChange()
      .subscribe((token: NbAuthJWTToken) => {
        this.token = token.toString();
        this.menu = this.getItemList();
      });
  }
  menu = [];

  getItemList(): NbMenuItem[] {
    return [
      {
        title: 'Change locations',
        icon: 'nb-shuffle',
        link: '/pages/location',
        home: true,
      },
      {
        title: 'Cafetaria Vikas',
        icon: 'nb-location',
        link: '/pages/dashboard',
      },
      {
        title: 'Manage',
        icon: 'nb-edit',
        link: '/pages/ui-features',
        children: [
          {
            title: 'Buttons',
            link: '/pages/ui-features/buttons',
          },
          {
            title: 'Grid',
            link: '/pages/ui-features/grid',
          },
          {
            title: 'Icons',
            link: '/pages/ui-features/icons',
          },
          {
            title: 'Modals',
            link: '/pages/ui-features/modals',
          },
          {
            title: 'Popovers',
            link: '/pages/ui-features/popovers',
          },
          {
            title: 'Typography',
            link: '/pages/ui-features/typography',
          },
          {
            title: 'Animated Searches',
            link: '/pages/ui-features/search-fields',
          },
          {
            title: 'Tabs',
            link: '/pages/ui-features/tabs',
          },
        ],
      },
      {
        title: 'Advertise',
        icon: 'nb-bar-chart',
        children: [
          {
            title: 'Form Inputs',
            link: '/pages/forms/inputs',
          },
          {
            title: 'Form Layouts',
            link: '/pages/forms/layouts',
          },
        ],
      },
    ];
  }
}
