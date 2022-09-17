import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { HelpMobileComponent } from './components/help-mobile/help-mobile.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { MemberBasicDetailsComponent } from './components/member-basic-details/member-basic-details.component';
import { MemberProfileComponent } from './components/member-profile/member-profile.component';
import { PremiumToLoginComponent } from './components/premium-to-login/premium-to-login.component';
import { PrivacyPolicyMobileComponent } from './components/privacy-policy-mobile/privacy-policy-mobile.component';
import { TermConditionMobileComponent } from './components/term-condition-mobile/term-condition-mobile.component';
import { AcceptedComponent } from './modules/inbox/components/accepted/accepted.component';
import { BlockMemberComponent } from './modules/inbox/components/block-member/block-member.component';
import { DeletedComponent } from './modules/inbox/components/deleted/deleted.component';
import { InboxDashboardComponent } from './modules/inbox/components/inbox-dashboard/inbox-dashboard.component';
import { InvitationsComponent } from './modules/inbox/components/invitations/invitations.component';
import { RequestsComponent } from './modules/inbox/components/requests/requests.component';
import { SentComponent } from './modules/inbox/components/sent/sent.component';
import { AccountComponent } from './modules/member/components/account/account.component';
import { MemberDashboardComponent } from './modules/member/components/member-dashboard/member-dashboard.component';
import { SearchDashboardComponent } from './modules/search/components/search-dashboard/search-dashboard.component';
import { AgmCoreModule} from '@agm/core'
// import { GooglePlaceModule } from 'ngx-google-places-autocomplete'


const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'AdminLogin', component: AdminLoginComponent },
  { path: 'members', component: MemberDashboardComponent },
  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  { path: 'help', component: HelpMobileComponent },
  { path: 'term-condition', component: TermConditionMobileComponent },
  { path: 'privacy-policy', component: PrivacyPolicyMobileComponent },
  { path: 'premium-update', component: PremiumToLoginComponent },
 //++++++++++++++++++++++++++++++++++++++++++++++login member basic details

  { path: 'member_details_login/:user_id', component: MemberBasicDetailsComponent },

  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  { path: 'member-profile/:member_id', component: MemberProfileComponent},
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
  {path:'admin', loadChildren:()=> import('./modules/admin/admin/admin.module').then((m)=>m.AdminModule)},
  { path :'**', component:LandingPageComponent},
];

@NgModule({
  imports: 
  [RouterModule.forRoot(routes, {useHash:true})
    ,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyACMBuuNWb4Pyx476Tbth7J-y4OeW4hvUA'
  }),
  
  ],


  exports: [RouterModule]

})
export class AppRoutingModule { }
