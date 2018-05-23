import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";


import { MessagingService } from "app/services/messaging.service";
import { Type } from "app/models/type";

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  types: Type[] = [
    new Type(1, 'one', 10, 9.99, true, false, 2, {name: 'Cafetaria Vikas', id: 1}),
    new Type(2, 'dos', 10, 9.99, true, false, 2, {name: 'Cafetaria Vikas', id: 1}),
    new Type(3, 'trois', 10, 9.99, true, false, 2, {name: 'Cafetaria Vikas', id: 1}),
    new Type(4, 'quatre', 10, 9.99, true, false, 2, {name: 'Cafetaria Vikas', id: 1}),
  ];
  constructor() { }

  getTypes(): Observable<Type[]> {
    MessagingService.send("Get all types");
    return of(this.types);
  }

  getType(id: number): Observable<Type> {
    MessagingService.send("Get type " + id);
    for (let i=0; i < this.types.length; i++) {
      if (this.types[i].id === id) {
        return of(this.types[i]);
      }
    }
    MessagingService.sendError("Get type " + id + " failed!")
  }

  deleteType(id: number): void {
    MessagingService.send("Delete type "+ id);
    for (let i = 0; i < this.types.length; i++) {
      if (this.types[i].id === id) {
        this.types.splice(i, 1);
        return;
      }
    }
    MessagingService.sendError("Delete type " + id + " failed!");
  }

  addType(name: string, time:number, price: number, active: boolean, exclusive: boolean, numberOfImages: number, locationId: number): Type {
    MessagingService.send("Add type " + name);
    let id = this.types[this.types.length - 1].id + 1;
    this.types.push(new Type(id, name, time, price, active, exclusive, numberOfImages, {name: 'CAFETARIA VIKAS', id: locationId}));
    return this.types[this.types.length - 1];
  }

  updateType(type: Type): Observable<any> {
    for (let i=0; i < this.types.length; i++) {
      if (this.types[i].id === type.id) {
        this.types[i] = type;
        break;
      }
    }
    return of([]);
  }
}
