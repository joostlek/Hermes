import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";
import {Promotion} from "app/models/promotion";
import {MessagingService} from "app/services/messaging.service";

@Injectable({
  providedIn: 'root'
})
export class PromotionService {
  promotions: Promotion[] = [
    new Promotion("asd", 1),
    new Promotion("uio", 2),
    new Promotion("qwe", 3),
    new Promotion("zxc", 4),
  ];
  constructor() { }

  getPromotions(): Observable<Promotion[]> {
    MessagingService.send("Get all promotions");
    return of(this.promotions);
  }

  getPromotion(id: number): Observable<Promotion> {
    MessagingService.send("Get promotion " + id);
    for (let i=0; i < this.promotions.length; i++) {
      if (this.promotions[i].id === id) {
        return of(this.promotions[i]);
      }
    }
    MessagingService.sendError("Get promotion " + id + " failed!")
  }

  deletePromotion(id: number): void {
    MessagingService.send("Delete promotion "+ id);
    for (let i = 0; i < this.promotions.length; i++) {
      if (this.promotions[i].id === id) {
        this.promotions.splice(i, 1);
        return;
      }
    }
    MessagingService.sendError("Delete promotion " + id + " failed!");
  }

  addPromotion(name: string): Promotion {
    MessagingService.send("Add promotion " + name);
    let id = this.promotions[this.promotions.length - 1].id + 1;
    this.promotions.push(new Promotion(name, id));
    return this.promotions[this.promotions.length - 1];
  }
}
