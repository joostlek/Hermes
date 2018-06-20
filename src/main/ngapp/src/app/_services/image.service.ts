import { Injectable } from '@angular/core';
import {Observable} from "rxjs/index";

import {Image} from "../_models/image";
import {MessagingService} from "app/_services/messaging.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  IMAGES: Image[] = [
    new Image(1, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
    new Image(2, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
    new Image(3, "asd", {name: 'asd', id: 1}, {name: 'Joost Lekkerkerker', id: 1}, [{'naam': 'Scherm 1', 'id': 1}], {height: 1080, width: 1920}, "", 5, true),
  ];
  constructor(private http: HttpClient) { }

  getImages(): Observable<Image[]> {
    MessagingService.send("Get all images");
    let url = 'api/v1/images/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Image[]>(url, {headers: httpHeaders});
  }

  getMyImages(): Observable<Image[]> {
    let url = 'api/v1/images/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Image[]>(url, {headers: httpHeaders});
  }

  getImage(id: number): Observable<Image> {
    MessagingService.send("Get image " + id);
    let url = 'api/v1/images/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Image>(url + id, {headers: httpHeaders});
  }

  deleteImage(image: Image): void {
    MessagingService.send("Delete image " + image.name);
    let url = 'api/v1/images/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': image.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
  }

  addImage(name: string, promotionId: number, screenId: number, userId: number, height: number, width: number, url: string, time: number): Observable<Image> {
    MessagingService.send("Add image " + name);
    let image = {
      name: name,
      height: height,
      width: width,
      url: url,
      time: time,
      screen: {
        id: screenId
      },
      owner: {
        id: userId
      },
      promotion: {
        id: promotionId
      }
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    let requestUrl = '/api/v1/images';
    return this.http.post<Image>(requestUrl, image, {headers: httpHeaders});
  }

  editImage(image: Image) {
    MessagingService.send("Edit image " + image.name);
    let url = 'api/v1/images';
    let body = {
      id: image.id,
      time: image.time,
      name: image.name
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.put(url, body, {headers: httpHeaders});
  }
}
