import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";


import {Image} from "../_models/image";
import {MessagingService} from "app/_services/messaging.service";
import {Promotion} from "app/_models/promotion";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  IMAGES: Image[] = [
    new Image(1, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
    new Image(2, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
    new Image(3, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
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

  addImage(name: string, promotionId: number, userId: number, height: number, width: number, url: string, time: number): void {
    MessagingService.send("Add image " + name);
    this.IMAGES.push(new Image(this.IMAGES.length + 1, name, {id: promotionId, name: 'PROMOTIE'}, {name: 'Joost Lekkerkerker', id: userId}, [], {width: width, height: height}, url, time, true));
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
