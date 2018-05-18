import { Component, OnInit } from '@angular/core';
import {TypeTableItem} from "@app/types/type-table/type-table-datasource";
import {TypeService} from "@app/services/type.service";

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.scss']
})
export class TypesComponent implements OnInit {
  types: TypeTableItem[];
  constructor(private typeService: TypeService) { }

  ngOnInit() {
    this.typeService.getTypes()
      .subscribe(types => this.types = types.map(type => type.toTable()))
  }

}
