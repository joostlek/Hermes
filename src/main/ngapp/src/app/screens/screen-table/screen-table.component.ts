import {Component, OnInit} from '@angular/core';
import {ScreenTableDataSource} from './screen-table-datasource';
import {ScreenService} from "@app/_services/screen.service";

@Component({
  selector: 'screen-table',
  templateUrl: './screen-table.component.html',
  styleUrls: ['./screen-table.component.css']
})
export class ScreenTableComponent implements OnInit {
  dataSource: ScreenTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'width', 'height', 'location', 'allowAds', 'more'];

  constructor (private screenService: ScreenService) { }
  ngOnInit() {
    this.dataSource = new ScreenTableDataSource(this.screenService.getScreens());
  }
}
