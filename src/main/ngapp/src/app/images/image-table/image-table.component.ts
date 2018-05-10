import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { ImageTableDataSource } from './image-table-datasource';
import {ImageService} from "../shared/image.service";

@Component({
  selector: 'image-table',
  templateUrl: './image-table.component.html',
  styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: ImageTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['imageId', 'name', 'active', 'promotion', 'username', 'width', 'height', ];

  constructor (private imageService: ImageService) {}

  ngOnInit() {
    this.dataSource = new ImageTableDataSource(this.paginator, this.sort, this.imageService);
  }
}
