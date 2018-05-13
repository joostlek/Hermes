import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ImageAddComponent} from "./image-add.component";

const routes: Routes = [
  {path: '', component: ImageAddComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImageAddRoutingModule { }
