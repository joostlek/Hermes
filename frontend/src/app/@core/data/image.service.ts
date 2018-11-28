import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Image} from './domain/image';
import {Screen} from './domain/screen';

@Injectable({
    providedIn: 'root'
})
export class ImageService {

    constructor(private http: HttpClient) {
    }

    getImages(): Observable<Image[]> {
        return this.http.get<Image[]>('api/images');
    }

    getImage(id: number): Observable<Image> {
        return this.http.get<Image>('api/images/' + id);
    }

    getImagesByScreen(screen: Screen): Observable<Image[]> {
        return this.getImagesByScreenId(screen.id);
    }

    getImagesByScreenId(screenId: number): Observable<Image[]> {
        return this.http.get<Image[]>('api/screens/' + screenId + '/images');
    }
}
