import {Component, OnInit} from '@angular/core';
import {ImageTableDataSource,} from './image-table-datasource';
import {ImageService} from "@app/_services/image.service";
import {Roles} from "@app/_models/roles";
import {User} from "@app/_models/user";

@Component({
  selector: 'image-table',
  templateUrl: './image-table.component.html',
  styleUrls: ['./image-table.component.css']
})
export class ImageTableComponent implements OnInit {
  dataSource: ImageTableDataSource;
  user: User;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'promotion', 'user', 'width', 'height', 'active', 'more'];

  constructor (private imageService: ImageService) {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    if (this.user.role === Roles.ROLE_SUPERUSER) {
      this.dataSource = new ImageTableDataSource(this.imageService.getImages());
    } else {
      this.dataSource = new ImageTableDataSource(this.imageService.getMyImages());
    }
  }
}
