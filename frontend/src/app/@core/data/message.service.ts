import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class MessageService {

    constructor() {
    }

    public log(message: string): void {
        console.log(message);
    }

    public error(message: string): void {
        console.error(message);
    }
}
