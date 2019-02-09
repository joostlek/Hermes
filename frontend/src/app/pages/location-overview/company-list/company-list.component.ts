import {Component, Input, OnInit} from '@angular/core';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-company-list',
    templateUrl: './company-list.component.html',
    styleUrls: ['./company-list.component.css'],
})
export class CompanyListComponent implements OnInit {
    @Input('companies') companies: Company[];

    constructor() {
    }

    ngOnInit() {
    }
}
