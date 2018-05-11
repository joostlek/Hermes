import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ImageService} from "../shared/image.service";
import {Image} from "../shared/image.model";

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent implements OnInit {
  image: Image;
  constructor(
    private route: ActivatedRoute,
    private imageService: ImageService,
    private location: Location,
  ) { }

  ngOnInit() {
    this.getHero();
  }

  getHero(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.imageService.getImage(id)
      .subscribe(image => this.image = image);
  }

}
