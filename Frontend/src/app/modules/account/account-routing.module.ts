import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './component/account/account.component';
import { ContactComponent } from './component/contact/contact.component';
import { DashboardAccountComponent } from './component/dashboard-account/dashboard-account.component';
import { HelpComponent } from './component/help/help.component';
import { HideDeleteProfileComponent } from './component/hide-delete-profile/hide-delete-profile.component';
import { PrivacyPolicyComponent } from './component/privacy-policy/privacy-policy.component';
import { PrivacyComponent } from './component/privacy/privacy.component';
import { TermsConComponent } from './component/terms-con/terms-con.component';


const routes: Routes = [
  {
    path: '', component: DashboardAccountComponent,
    children: [
      { path: 'setting', component: AccountComponent},
      { path: 'email-sms', component: ContactComponent},
      { path: 'help', component: HelpComponent},
      { path: 'HideDeleteProfile', component: HideDeleteProfileComponent},
      { path: 'privacy', component: PrivacyComponent},
      { path: 'privacy-policy', component: PrivacyPolicyComponent},
      { path: 'term-condition', component: TermsConComponent},
      {path:'', redirectTo:'/account/setting',pathMatch:'full'}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
