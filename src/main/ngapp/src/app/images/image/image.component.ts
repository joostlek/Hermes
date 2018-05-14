import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ImageService} from "../shared/image.service";
import {Image} from "../shared/image.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent implements OnInit {
  image: Image;
  formGroup: FormGroup;
  edit: boolean = false;
  time: number;

  constructor(
    private route: ActivatedRoute,
    private imageService: ImageService,
    private location: Location,
    private _FormBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.getImage();
    this.formGroup = this._FormBuilder.group({
      imageName: [this.image.name, Validators.required]
      }
    )
  }

  getImage(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.imageService.getImage(id)
      .subscribe(image => this.image = image);
  }

  goBack(): void {
    this.location.back();
  }

  deleteImage(): void {
    this.imageService.deleteImage(this.image);
    this.location.back();
  }

  editImage(): void {
    this.time = this.image.time;
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    this.image.name = this.formGroup.value['imageName'];
    this.image.time = this.time;
  }

  slide(event): void {
    this.time = event;
  }
}
