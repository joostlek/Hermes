import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs/index";
import {Promotion} from "app/_models/promotion";
import {MessagingService} from "app/_services/messaging.service";
import {AuthService} from "@app/_services/auth.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PromotionService {
  promotions: Promotion[] = [
    new Promotion({"id":13,"name":"ASD","startDate":"Jun 17, 2018, 9:52:10 PM","owner":{"id":9, 'firstName': 'kek'},"type":{"id":3,"name":'kek',"price":0,"active":false,"exclusive":false,"amountOfImages":0},"images":[]}),
  ];
  constructor(private auth: AuthService, private http: HttpClient) { }

  getPromotions(): Observable<Promotion[]> {
    MessagingService.send("Get all promotions");
    let url = 'api/v1/promotions/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Promotion[]>(url, {headers: httpHeaders})
      .pipe(
        tap(promotions => console.log('Fetched promotions')),
        catchError(this.handleError('GetPromotions', []))
      )
  }

  getMyPromotions(): Observable<Promotion[]> {
    let url = 'api/v1/promotions';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Promotion[]>(url, {headers: httpHeaders});
  }

  getPromotion(id: number): Observable<Promotion> {
    MessagingService.send("Get promotion " + id);
    let url = 'api/v1/promotions/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
   return this.http.get<Promotion>(url + id, {headers: httpHeaders});
  }

  deletePromotion(promotion: Promotion): void {
    MessagingService.send("Delete promotion "+ promotion.id);
    let url = 'api/v1/promotions/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': promotion.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
    // MessagingService.sendError("Delete promotion " + promotion.id + " failed!");
  }


  addPromotion(name: string, type: number, location: number, user: number, startDate: Date): Observable<Promotion> {
    MessagingService.send("Add promotion " + name);
    // let id = this.promotions[this.promotions.length - 1].id + 1;
    // let promotion: Promotion = new Promotion(id, name, {'name': 'TYPE 1', 'id': 1}, {'name':'Joost Lekkerkerker', 'id': 1}, [], {name: 'CAFETARIA VIKAS', id: 1}, '19-05-2018');
    // this.promotions.push(promotion);
    // let us: User = JSON.parse(localStorage.getItem('user'));
    // us.promotions.push(promotion);
    // localStorage.setItem('user', JSON.stringify(us));
    let promotion = {
      name: name,
      startDate: "Jun 17, 2018, 9:52:10 PM",
      owner: {
        id: user
      },
      type: {
        id: type
      }
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    let url = '/api/v1/promotions';
    return this.http.post<Promotion>(url, promotion, {headers: httpHeaders});
  }

  updatePromotion(promotion: Promotion) {
    let url = 'api/v1/promotions/';
    let body = {
      id: promotion.id,
      name: promotion.name
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.put(url, body, {headers: httpHeaders});
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
