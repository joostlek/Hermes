import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";


import {Image} from "./image.model";
import {MessagingService} from "@app/shared/messaging.service";
import {Promotion} from "@app/promotions/shared/promotion.model";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  IMAGES: Image[] = [
    new Image(1, "asd", "new", 1, "joostlek", 1,[{'naam': 'Scherm 1', 'id': 1}], 1080, 1920, "", 5, true),
    new Image(2, "asdd", "new", 2, "joostlek", 1, [{'naam': 'Scherm 1', 'id': 1}],  1080, 1920, "", 4,false),
    new Image(3, "asddd", "new", 1, "joostlek", 1, [{'naam': 'Scherm 1', 'id': 1}],  1080, 1920, "", 9,true),
  ];
  constructor() { }

  getImages(): Observable<Image[]> {
    MessagingService.send("Get all images");
    return of(this.IMAGES)
  }

  getImage(id: number): Observable<Image> {
    MessagingService.send("Get image " + id);
    for (let i = 0; i < this.IMAGES.length; i++) {
      if (this.IMAGES[i].id === id) {
        return of(this.IMAGES[i]);
      }
    }
    MessagingService.sendError("Get image " + id + " failed!");
  }

  deleteImage(image: Image): void {
    MessagingService.send("Delete image " + image.name);
    for (let i=0; i < this.IMAGES.length; i++) {
      if (this.IMAGES[i] === image) {
        this.IMAGES.splice(i,1);
        return;
      }
    }
    MessagingService.sendError("Delete image " + image.name + " failed!");
  }

  addImage(imageName: string, promotion: Promotion, user_id: number, height: number, width: number, url: string, time: number): void {
    MessagingService.send("Add image " + imageName);
    let image_id = this.IMAGES[this.IMAGES.length - 1].id + 1;
    let image = new Image(image_id, imageName, promotion.name, promotion.id, "joostlek", user_id, [], height, width, url, time, true);
    this.IMAGES.push(image);
  }

  editImage(image: Image): void {
    MessagingService.send("Edit image " + image.name);
    for (let i=0; i < this.IMAGES.length; i++) {
      if (this.IMAGES[i].id === image.id) {
        this.IMAGES[i] = image;
        return;
      }
    }
    MessagingService.sendError("Edit image " + image.name + " failed!");
  }
}
