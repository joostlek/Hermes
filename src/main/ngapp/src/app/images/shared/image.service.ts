import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";


import {Image} from "./image.model";
import {forEach} from "@angular/router/src/utils/collection";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  IMAGES: Image[] = [
    new Image(1, "asd", "new", 1, "joostlek", 1,["Scherm 1"], 1080, 1920, "", true),
    new Image(2, "asdd", "new", 2, "joostlek", 1, ["Scherm 1"], 1080, 1920, "", true),
    new Image(3, "asddd", "new", 1, "joostlek", 1, ["Scherm 1"], 1080, 1920, "", true),
  ];
  constructor() { }

  getImages(): Observable<Image[]> {
    return of(this.IMAGES)
  }

  getImage(id: Number): Observable<Image> {
    for (let i = 0; i < this.IMAGES.length; i++) {
      if (this.IMAGES[i].imageId === id) {
        return of(this.IMAGES[i]);
      }
    }
  }

}
