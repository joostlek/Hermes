import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ImagesRoutingModule } from './images-routing.module';
import { ImageTableComponent } from './image-table/image-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule} from '@angular/material';
import { ImageComponent } from './image/image.component';
import { ImagesComponent } from './images.component';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    ImagesRoutingModule,
  ],
  declarations: [ImageTableComponent, ImageComponent, ImagesComponent]
})
export class ImagesModule { }
