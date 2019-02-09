import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class FileUploadService {

    constructor(
        private http: HttpClient,
    ) {
    }

    uploadFile(fileToUpload: File): Observable<string> {
        const endpoint = '/api/images/upload';
        const formData: FormData = new FormData();
        formData.append('file', fileToUpload, fileToUpload.name);
        return this.http.post<any>(endpoint, formData)
            .pipe(map((response: any) => {
                return response.url;
            }));
    }

}
