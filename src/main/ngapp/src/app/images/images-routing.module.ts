import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ImagesComponent} from "./images.component";
import {ImageComponent} from "./image/image.component";

const routes: Routes = [
  {path: '', component: ImagesComponent},
  {path: ':id', component: ImageComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImagesRoutingModule { }
