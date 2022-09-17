import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from '../component/admin-dashboard/admin-dashboard.component';
import { AllCountMatchesComponent } from '../component/all-count-matches/all-count-matches.component';
import { DashboardComponent } from '../component/dashboard/dashboard.component';
import { MemberDetailsShowComponent } from '../component/member-details-show/member-details-show.component';
import { UpdatePremiumComponent } from '../component/update-premium/update-premium.component';


const routes: Routes = [
  {
    path: '', component: DashboardComponent,
    children: [
      { path: 'setting', component: AdminDashboardComponent},
      { path: 'allCount-matches', component: AllCountMatchesComponent},
      { path: 'primium-update', component: UpdatePremiumComponent },
      { path: 'member-details/:member_id', component: MemberDetailsShowComponent },
      {path:'', redirectTo:'/admin/setting',pathMatch:'full'}
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
