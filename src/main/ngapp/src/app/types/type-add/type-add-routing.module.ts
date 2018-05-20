import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TypeAddComponent} from "@app/types/type-add/type-add.component";

const routes: Routes = [
  {path: '', component: TypeAddComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypeAddRoutingModule { }
