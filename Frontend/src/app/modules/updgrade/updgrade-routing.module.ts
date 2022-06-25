import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdgradeDashboardComponent } from './component/updgrade-dashboard/updgrade-dashboard.component';
import { UpdgradeComponent } from './component/updgrade/updgrade.component';

const routes: Routes = [
  {
    path: '', component: UpdgradeDashboardComponent,
    children: [
      { path: 'plan', component: UpdgradeComponent},
      {path:'', redirectTo:'/upgrade/plan',pathMatch:'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UpdgradeRoutingModule { }
