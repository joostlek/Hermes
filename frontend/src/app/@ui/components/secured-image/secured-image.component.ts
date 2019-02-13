import {HttpClient} from '@angular/common/http';
import {Component, Input, OnChanges} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {BehaviorSubject, Observable} from 'rxjs';
import {filter, map, switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-secured-image',
    templateUrl: './secured-image.component.html',
    styleUrls: ['./secured-image.component.css'],
})
export class SecuredImageComponent implements OnChanges {

    constructor(private httpClient: HttpClient,
                private dom: DomSanitizer) {
    }

    @Input() private src: string;
    @Input() style: any;
    private src$ = new BehaviorSubject(this.src);

    dataUrl$ = this.src$
        .pipe(
            filter((value) => value !== null),
            switchMap(
                (url) => this.loadImage(url),
            ),
        );

    ngOnChanges(): void {
        this.src$.next(this.src);
    }

    private loadImage(url: string): Observable<any> {
        return this.httpClient
            .get('api/files/' + url, {responseType: 'blob'})
            .pipe(map((e) => this.dom.bypassSecurityTrustUrl(URL.createObjectURL(e))));
    }
}
