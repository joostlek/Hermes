import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";
import {Promotion} from "@app/promotions/shared/promotion.model";

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
    return of(this.promotions );
  }
}
