import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersComponent} from "@app/users/users.component";
import {UserComponent} from "@app/users/user/user.component";

const routes: Routes = [
  {
    path: '',
    component: UsersComponent
  },
  {
    path: ':id',
    component: UserComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
