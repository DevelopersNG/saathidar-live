import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';

import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { InboxDashboardComponent } from './modules/inbox/components/inbox-dashboard/inbox-dashboard.component';
import { AccountComponent } from './modules/member/components/account/account.component';
import { MemberDashboardComponent } from './modules/member/components/member-dashboard/member-dashboard.component';
import { SearchDashboardComponent } from './modules/search/components/search-dashboard/search-dashboard.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'members', component: MemberDashboardComponent },
  { path: 'matches', component: SearchDashboardComponent },
  { path: 'inbox', component: InboxDashboardComponent },
  { path: 'account', component: AccountComponent },
  { path:'' , redirectTo:'/login',pathMatch:'full'},
  {path:'members', loadChildren:()=> import('./modules/member/member.module').then((m)=> m.MemberModule)},
  {path:'matches', loadChildren:()=> import('./modules/search/search.module').then((m)=> m.SearchModule)},
  {path:'inbox', loadChildren:()=> import('./modules/inbox/inbox.module').then((m)=> m.InboxModule)},
  {path:'account', loadChildren:()=> import('./modules/account/account.module').then((m)=> m.AccountModule)},
  {path:'upgrade', loadChildren:()=> import('./modules/updgrade/updgrade.module').then((m)=>m.UpdgradeModule)},
  {path:'member', loadChildren:()=> import('./modules/searchMember/searchmember.module').then((m)=>m.SearchmemberModule)},
  { path :'**', component:LandingPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})



export class AppRoutingModule { }
