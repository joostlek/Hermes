import {ErrorHandler, Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable()
export class ErrorsHandler implements ErrorHandler {

    handleError(error: any): void {
        if (error instanceof HttpErrorResponse) {
            console.error('Backend returned status code: ', error.status);
            console.error('Response body', error.message);
        } else {
            console.error('An error occurred: ', error.message);
        }
    }
}
