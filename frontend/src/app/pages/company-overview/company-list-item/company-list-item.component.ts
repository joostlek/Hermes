import {Component, Input, OnInit} from '@angular/core';
import {Company} from '../../../@core/data/domain/company';

@Component({
    selector: 'app-company-list-item',
    templateUrl: './company-list-item.component.html',
    styleUrls: ['./company-list-item.component.css']
})
export class CompanyListItemComponent implements OnInit {
    @Input('company') company: Company;

    constructor() {
    }

    ngOnInit() {
    }

}
