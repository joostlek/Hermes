import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";


import {Image} from "./image.model";

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
}
