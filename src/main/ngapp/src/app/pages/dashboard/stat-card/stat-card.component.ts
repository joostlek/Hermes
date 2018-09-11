import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'ngx-stat-card',
  templateUrl: './stat-card.component.html',
  styleUrls: ['./stat-card.component.scss']
})
export class StatCardComponent implements OnInit {
  @Input('name') name: string;
  @Input('amount') amount: number;

  constructor() { }

  ngOnInit() {
  }

}
