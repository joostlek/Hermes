export function calculateDifference(date1: Date, date2: Date): Interval {
    if (date2 > date1) {
        return calculateDifference(date2, date1);
    }
    const interval = new Interval();
    interval.years = date1.getFullYear() - date2.getFullYear();
    interval.months = date1.getMonth() - date2.getMonth();
    interval.days = date1.getDate() - date2.getDate();
    interval.hours = date1.getHours() - date2.getHours();
    interval.minutes = date1.getMinutes() - date2.getMinutes();
    interval.seconds = date1.getSeconds() - date2.getSeconds();
    return interval;
}

export class Interval {
    private _years: number;
    private _months: number;
    private _days: number;
    private _hours: number;
    private _minutes: number;
    private _seconds: number;


    get years(): number {
        return this._years;
    }

    set years(value: number) {
        this._years = value;
    }

    get months(): number {
        return this._months;
    }

    set months(value: number) {
        this._months = value;
    }

    get days(): number {
        return this._days;
    }

    set days(value: number) {
        this._days = value;
    }

    get hours(): number {
        return this._hours;
    }

    set hours(value: number) {
        this._hours = value;
    }

    get minutes(): number {
        return this._minutes;
    }

    set minutes(value: number) {
        this._minutes = value;
    }

    get seconds(): number {
        return this._seconds;
    }

    set seconds(value: number) {
        this._seconds = value;
    }

    toString(): string {
        let res = '';
        if (this.years !== 0) {
            res += this.years + ' years';
        }
        if (this.months !== 0) {
            if (res.length !== 0) {
                res += ',';
            }
            res += this.months + ' months';
        }
        if (this.days !== 0 || res === '') {
            if (res.length !== 0) {
                res += ',';
            }
            res += this.days + ' days';
        }
        return res;
    }
}