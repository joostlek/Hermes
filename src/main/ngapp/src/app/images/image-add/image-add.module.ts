import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ImageAddRoutingModule } from './image-add-routing.module';
import { ImageAddComponent } from './image-add.component';

@NgModule({
  imports: [
    CommonModule,
    ImageAddRoutingModule
  ],
  declarations: [ImageAddComponent]
})
export class ImageAddModule { }
