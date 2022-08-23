import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchmemberRoutingModule } from './searchmember-routing.module';
import { MembersearchDashboardComponent } from './component/membersearch-dashboard/membersearch-dashboard.component';
import { MembersearchHeaderComponent } from './component/membersearch-header/membersearch-header.component';
import { MembersearchFooterComponent } from './component/membersearch-footer/membersearch-footer.component';


@NgModule({
  declarations: [
    MembersearchDashboardComponent,
    MembersearchHeaderComponent,
    MembersearchFooterComponent
  ],
  imports: [
    CommonModule,
    SearchmemberRoutingModule
  ]
})
export class SearchmemberModule { }
