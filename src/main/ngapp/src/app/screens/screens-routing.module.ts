import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ScreensComponent} from "@app/screens/screens.component";

const routes: Routes = [
  {path: '', component: ScreensComponent},
  {path: 'add', loadChildren: './screen-add/screen-add.module#ScreenAddModule'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ScreensRoutingModule { }
