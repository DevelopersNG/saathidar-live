import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountRoutingModule } from './account-routing.module';
import { HeaderAccountComponent } from './component/header-account/header-account.component';
import { FooterAccountComponent } from './component/footer-account/footer-account.component';
import { AccountComponent } from './component/account/account.component';
import { ContactComponent } from './component/contact/contact.component';
import { DashboardAccountComponent } from './component/dashboard-account/dashboard-account.component';
import { HideDeleteProfileComponent } from './component/hide-delete-profile/hide-delete-profile.component';
import { HelpComponent } from './component/help/help.component';
import { FormsModule } from '@angular/forms';
import { PrivacyComponent } from './component/privacy/privacy.component';
import { TermsConComponent } from './component/terms-con/terms-con.component';
import { PrivacyPolicyComponent } from './component/privacy-policy/privacy-policy.component';


@NgModule({
  declarations: [
    HeaderAccountComponent,
    FooterAccountComponent,
    AccountComponent,
    ContactComponent,
    DashboardAccountComponent,
    HideDeleteProfileComponent,
    HelpComponent,
    PrivacyComponent,
    TermsConComponent,
    PrivacyPolicyComponent,
    

  ],
  imports: [
    FormsModule,
    CommonModule,
    AccountRoutingModule,
  ]
})
export class AccountModule { }
