import {Component} from '@angular/core';

import {MENU_ITEMS} from './pages-menu';
import {NbMenuItem} from '@nebular/theme';
import {UserService} from '../@core/data/users.service';

@Component({
  selector: 'ngx-pages',
  template: `
    <ngx-sample-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-sample-layout>
  `
})
export class PagesComponent {

  user: any;

  constructor(private userService: UserService) {
    console.log('kek');
    this.userService.getUsers()
      .subscribe((users: any) => {
        this.user = users.nick;
        this.menu = this.getItemList();
      });

  }
  menu = [];

  getItemList(): NbMenuItem[] {
    return [
      {
        title: 'Change locations',
        icon: 'nb-e-commerce',
        link: '/pages/dashboard',
        home: true,
      },
      {
        title: this.user['name'],
        group: true,
      },
      {
        title: 'UI Features',
        icon: 'nb-keypad',
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
        title: 'Forms',
        icon: 'nb-compose',
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
      {
        title: 'Components',
        icon: 'nb-gear',
        children: [
          {
            title: 'Tree',
            link: '/pages/components/tree',
          }, {
            title: 'Notifications',
            link: '/pages/components/notifications',
          },
        ],
      },
      {
        title: 'Maps',
        icon: 'nb-location',
        children: [
          {
            title: 'Google Maps',
            link: '/pages/maps/gmaps',
          },
          {
            title: 'Leaflet Maps',
            link: '/pages/maps/leaflet',
          },
          {
            title: 'Bubble Maps',
            link: '/pages/maps/bubble',
          },
          {
            title: 'Search Maps',
            link: '/pages/maps/searchmap',
          },
        ],
      },
      {
        title: 'Charts',
        icon: 'nb-bar-chart',
        children: [
          {
            title: 'Echarts',
            link: '/pages/charts/echarts',
          },
          {
            title: 'Charts.js',
            link: '/pages/charts/chartjs',
          },
          {
            title: 'D3',
            link: '/pages/charts/d3',
          },
        ],
      },
      {
        title: 'Editors',
        icon: 'nb-title',
        children: [
          {
            title: 'TinyMCE',
            link: '/pages/editors/tinymce',
          },
          {
            title: 'CKEditor',
            link: '/pages/editors/ckeditor',
          },
        ],
      },
      {
        title: 'Tables',
        icon: 'nb-tables',
        children: [
          {
            title: 'Smart Table',
            link: '/pages/tables/smart-table',
          },
        ],
      },
      {
        title: 'Miscellaneous',
        icon: 'nb-shuffle',
        children: [
          {
            title: '404',
            link: '/pages/miscellaneous/404',
          },
        ],
      },
      {
        title: 'Auth',
        icon: 'nb-locked',
        children: [
          {
            title: 'Login',
            link: '/auth/login',
          },
          {
            title: 'Register',
            link: '/auth/register',
          },
          {
            title: 'Request Password',
            link: '/auth/request-password',
          },
          {
            title: 'Reset Password',
            link: '/auth/reset-password',
          },
        ],
      },
    ];
  }
}
