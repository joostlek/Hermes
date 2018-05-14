import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule,
  MatFormFieldModule, MatInputModule, MatOptionModule, MatSelectModule, MatSliderModule
} from '@angular/material';

import { ImagesRoutingModule } from './images-routing.module';
import { ImageTableComponent } from './image-table/image-table.component';
import { ImageComponent } from './image/image.component';
import { ImagesComponent } from './images.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatSliderModule,
    ImagesRoutingModule,
  ],
  declarations: [ImageTableComponent, ImageComponent, ImagesComponent]
})
export class ImagesModule { }
