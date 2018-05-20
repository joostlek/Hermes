import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { UserTableComponent } from './user-table/user-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    UsersRoutingModule,
  ],
  declarations: [UsersComponent, UserTableComponent]
})
export class UsersModule { }
