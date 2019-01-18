import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable, Injector} from '@angular/core';
import {Router} from '@angular/router';
import {EMPTY, Observable, Subject, throwError} from 'rxjs';
import {catchError, map, switchMap} from 'rxjs/operators';
import {AuthService} from '../data/auth.service';
import {TokenService} from '../data/token.service';

@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {

    authService: AuthService;
    refreshTokenInProgress = false;

    tokenRefreshedSource = new Subject();
    tokenRefreshed$ = this.tokenRefreshedSource.asObservable();

    constructor(
        private injector: Injector,
        private router: Router,
    ) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.authService = this.injector.get(AuthService);
        req = this.addAuthHeader(req);
        if (req.url.endsWith('auth/user/refresh')) {
            return next.handle(req).pipe(
                catchError((err, caught) => {
                    this.router.navigateByUrl('auth/login');
                    return throwError(err);
                }),
            );
        }
        return next.handle(req).pipe(
            catchError((err: any, caught: Observable<HttpEvent<any>>): Observable<any> => {
                if (err.status === 403) {
                    return this.refreshToken()
                        .pipe(
                            switchMap(() => {
                                req = this.addAuthHeader(req);
                                return next.handle(req);
                            }),
                            catchError((err1, caught1) => {
                                this.authService.logout();
                                return EMPTY;
                            }),
                        );
                }
            }),
        );
    }

    refreshToken(): Observable<any> {
        if (this.refreshTokenInProgress) {
            return new Observable((observer) => {
                this.tokenRefreshed$.subscribe(() => {
                    observer.next();
                    observer.complete();
                });
            });
        } else {
            this.refreshTokenInProgress = true;

            return this.authService.refresh().pipe(
                map(() => {
                    this.refreshTokenInProgress = false;
                    this.tokenRefreshedSource.next();
                }));
        }
    }

    addAuthHeader(request) {
        const token = this.injector.get(TokenService).getToken();
        if (token) {
            return request.clone({
                headers: request.headers.set('Authorization', 'Bearer ' + token),
            });
        }
        return request;
    }
}
