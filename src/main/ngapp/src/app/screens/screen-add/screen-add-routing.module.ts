import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ScreenAddComponent} from "@app/screens/screen-add/screen-add.component";

const routes: Routes = [
  {path: '', component: ScreenAddComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ScreenAddRoutingModule { }
