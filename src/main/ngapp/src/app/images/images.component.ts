import { Component, OnInit } from '@angular/core';


import {ImageService} from "@app/_services/image.service";
import {ImageTableItem} from "@app/images/image-table/image-table-datasource";

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.scss']
})
export class ImagesComponent implements OnInit {
  images: ImageTableItem[];
  constructor(private imageService: ImageService) { }

  ngOnInit() {
    this.imageService.getImages()
      .subscribe(images => this.images = images.map(image => image.toTable()));
  }

}
