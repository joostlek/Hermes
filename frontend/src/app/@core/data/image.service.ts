import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Image} from './domain/image';
import {Screen} from './domain/screen';
import {User} from './domain/user';

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

    getImagesByUser(user: User): Observable<Image[]> {
        return this.getImagesByUserId(user.id);
    }

    getImagesByUserId(userId: number): Observable<Image[]> {
        return this.http.get<Image[]>('api/users/' + userId + '/images');
    }

    getImagesByLocationIdByUserId(locationId: number, userId: number): Observable<Image[]> {
        return this.http.get<Image[]>('api/users/' + userId + '/locations/' + locationId + '/images');
    }

    addImage(name: string, url: string, screenId: number, userId: number): Observable<Image> {
        const image = new Image();
        image.name = name;
        image.url = url;
        return this.http.post<Image>('/api/users/' + userId + '/images?screenId=' + screenId, image);
    }

    getImagesByLocationId(locationId: number): Observable<Image[]> {
        return this.http.get<Image[]>('api/locations/' + locationId + '/images');
    }
}
