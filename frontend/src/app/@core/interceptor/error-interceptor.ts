import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AuthService} from '../data/auth.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(
        private authService: AuthService,
        private router: Router,
    ) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                catchError(
                    (err) => {
                        if (err.status === 401) {
                            this.authService.logout()
                                .subscribe(
                                    () => {
                                        this.router.navigateByUrl('/auth/login');
                                    },
                                );
                        }
                        const error = err.error.message || err.statusText;
                        return throwError(error);
                    },
                ),
            );
    }
}
