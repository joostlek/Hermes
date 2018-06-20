import {Component, OnInit} from '@angular/core';
import {ImageTableDataSource,} from './image-table-datasource';
import {ImageService} from "@app/_services/image.service";

@Component({
  selector: 'image-table',
  templateUrl: './image-table.component.html',
  styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
  dataSource: ImageTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'promotion', 'user', 'width', 'height', 'active', 'more'];

  constructor (private imageService: ImageService) {}

  ngOnInit() {
    this.dataSource = new ImageTableDataSource(this.imageService.getImages());
  }
}
