import { Component, OnInit } from '@angular/core';
import {ScreenTableItem} from "@app/screens/screen-table/screen-table-datasource";
import {ScreenService} from "@app/_services/screen.service";

@Component({
  selector: 'app-screens',
  templateUrl: './screens.component.html',
  styleUrls: ['./screens.component.scss']
})
export class ScreensComponent implements OnInit {
  screens: ScreenTableItem[];
  constructor(private screenService: ScreenService) { }

  ngOnInit() {
    this.screenService.getScreens()
      .subscribe(screens => this.screens = screens.map(screen => screen.toTable()));
  }

}
