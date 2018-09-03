import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TypesComponent} from "@app/types/types.component";
import {TypeComponent} from "@app/types/type/type.component";

const routes: Routes = [
  {path: '', component: TypesComponent},
  {path: 'add', loadChildren: './type-add/type-add.module#TypeAddModule'},
  {path: ':id', component: TypeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypesRoutingModule { }
