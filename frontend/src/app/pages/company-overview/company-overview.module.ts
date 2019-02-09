import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyOverviewRoutingModule } from './company-overview-routing.module';
import { CompanyOverviewComponent } from './company-overview.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompanyListItemComponent } from './company-list-item/company-list-item.component';

@NgModule({
  declarations: [CompanyOverviewComponent, CompanyListComponent, CompanyListItemComponent],
  imports: [
    CommonModule,
    CompanyOverviewRoutingModule
  ]
})
export class CompanyOverviewModule { }
