import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-screen-add',
  templateUrl: './screen-add.component.html',
  styleUrls: ['./screen-add.component.scss']
})
export class ScreenAddComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goBack() {
    this.location.back();
  }

}
