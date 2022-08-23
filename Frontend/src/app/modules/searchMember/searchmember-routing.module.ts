import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MembersearchDashboardComponent } from './component/membersearch-dashboard/membersearch-dashboard.component';
import { MembersearchComponent } from './component/membersearch/membersearch.component';


const routes: Routes = [
  {
    path: '', component: MembersearchDashboardComponent,
    children: [
      { path: 'membersearch', component: MembersearchComponent},
      {path:'', redirectTo:'/member/membersearch',pathMatch:'full'}
    ]
  }
 
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchmemberRoutingModule { }
