import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ImageService} from "@app/_services/image.service";
import {Image} from "@app/_models/image";
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
      imageName: [Validators.required]
      }
    )
  }

  getImage(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.imageService.getImage(id)
      .subscribe(image => {
        this.image = image
        console.log(this.image.name)

      });
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
    console.log(this.formGroup)
    this.image.name = this.formGroup.get('imageName').value;
    this.image.time = this.time;
    this.imageService.editImage(this.image)
      .subscribe(result => console.log(result));
  }

  slide(event): void {
    this.time = event;
  }
}
