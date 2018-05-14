import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-image-add',
  templateUrl: './image-add.component.html',
  styleUrls: ['./image-add.component.scss']
})
export class ImageAddComponent implements OnInit {

  constructor(
    private location: Location,
  ) { }

  ngOnInit() {  }

  goBack(): void {
    this.location.back();
  }
}
