import { Injectable } from '@angular/core';
import {environment} from "environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  constructor() { }

  public static send(message: String): void {
    if (!environment.production) {
      console.log(message);
    }
  }

  public static sendError(message: String): void {
    if (!environment.production) {
      console.error(message);
    }
  }
}
