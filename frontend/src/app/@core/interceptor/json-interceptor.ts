import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

// https://github.com/angular/angular/blob/master/packages/common/http/src/xhr.ts#L18
const XSSI_PREFIX = /^\)\]\}',?\n/;

@Injectable()
export class JsonInterceptor implements HttpInterceptor {

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.responseType !== 'json') {
            return next.handle(req);
        }
        // convert to responseType of text to skip angular parsing
        req = req.clone({
            responseType: 'text',
        });

        return next.handle(req)
            .pipe(map((event) => {
                // Pass through everything except for the final response.
                if (!(event instanceof HttpResponse)) {
                    return event;
                }
                return this.processJsonResponse(event);
            }));
    }

    private processJsonResponse(res: HttpResponse<string>): HttpResponse<any> {
        let body = res.body;
        if (typeof body === 'string') {
            const originalBody = body;
            body = body.replace(XSSI_PREFIX, '');
            try {
                body = body !== '' ? JSON.parse(body, (key: any, value: any) => this.reviveUtcDate(key, value)) : null;
            } catch (error) {
                // match https://github.com/angular/angular/blob/master/packages/common/http/src/xhr.ts#L221
                throw new HttpErrorResponse({
                    error: {error, text: originalBody},
                    headers: res.headers,
                    status: res.status,
                    statusText: res.statusText,
                    url: res.url || undefined,
                });
            }
        }
        return res.clone({body});
    }

    private reviveUtcDate(key: any, value: any): any {
        if (typeof value !== 'string') {
            return value;
        }
        if (value === '0001-01-01T00:00:00') {
            return null;
        }
        const match = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)\+\d{4}$/.exec(value);
        if (!match) {
            return value;
        }
        return new Date(value);
    }
}
