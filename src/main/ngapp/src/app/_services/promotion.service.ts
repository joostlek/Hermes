import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";
import {Promotion} from "app/_models/promotion";
import {MessagingService} from "app/_services/messaging.service";

@Injectable({
  providedIn: 'root'
})
export class PromotionService {
  promotions: Promotion[] = [
    new Promotion(1, "Promotion 1", {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [{'name': "asd"}, {'name': "qwe"}], '19-05-2018'),
    new Promotion(2, "Promotion 2", {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [{'name': "asd"}, {'name': "qwe"}], '19-05-2018'),
    new Promotion(3, "Promotion 3", {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [{'name': "asd"}, {'name': "qwe"}], '19-05-2018'),
    new Promotion(4, "Promotion 4", {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [{'name': "asd"}, {'name': "qwe"}], '19-05-2018'),
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
    this.promotions.push(new Promotion(id, name, {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [], '19-05-2018'));
    return this.promotions[this.promotions.length - 1];
  }
}
