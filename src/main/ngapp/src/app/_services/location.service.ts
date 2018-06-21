import { Injectable } from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";
import { Location } from '@app/_models/location';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  LOCATIONS: Location[] = [
    new Location(1, 'Cafetaria Vikas', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
    new Location(2, 'Cafetaria de Snack', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
    new Location(3, 'Cafetaria de nogwat', {'name':'Vikas Chhabra', 'id': 1}, [{'id':1}], 'Kalverstraat', '1', '3481ES', 'Harmelen', 'Nederland'),
  ];
  constructor(private http: HttpClient) { }

  getLocations(): Observable<Location[]> {
    let url = '/api/v1/locations/all';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Location[]>(url, {headers: httpHeaders});
  }

  getMyLocations(): Observable<Location[]> {
    let url = '/api/v1/locations';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Location[]>(url, {headers: httpHeaders});
    
  }


  addLocation(name: string, ownerId: number, street: string, houseNumber: string, zipCode: string, city: string, country: string) {
    let location = {
      name: name,
      street: street,
      houseNumber: houseNumber,
      zipCode: zipCode,
      city: city,
      country: country,
      owner: {
        id: ownerId
      }
    };
    let url = '/api/v1/locations';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.post<Location>(url, location, {headers: httpHeaders});
  }

  getLocation(id: number): Observable<Location> {
    let url = '/api/v1/locations/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.get<Location>(url + id, {headers: httpHeaders});
  }

  deleteLocation(location: Location) {
    let url = 'api/v1/locations/';
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    this.http.request('DELETE', url, {body: {'id': location.id}, headers: httpHeaders})
      .subscribe(object => console.log(object));
  }

  updateLocation(location: Location) {
    let url = 'api/v1/locations/';
    let body = {
      id: location.id,
      name: location.name,
      street: location.street,
      houseNumber: location.houseNumber,
      zipCode: location.zipCode,
      city: location.city,
      country: location.country
    };
    let httpHeaders = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token'))
    });
    return this.http.put(url, body, {headers: httpHeaders});
  }

  getSimpleLocations(): Observable<Location[]> {
    let url = 'api/v1/locations/simple';
    let httpHeaders = new HttpHeaders({
      'Authorization': JSON.parse(localStorage.getItem('token')),
    });
    return this.http.get<Location[]>(url, {headers: httpHeaders});
  }
}
