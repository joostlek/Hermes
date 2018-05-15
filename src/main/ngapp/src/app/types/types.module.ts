import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypesRoutingModule } from './types-routing.module';
import { TypesComponent } from './types.component';

@NgModule({
  imports: [
    CommonModule,
    TypesRoutingModule
  ],
  declarations: [TypesComponent]
})
export class TypesModule { }
