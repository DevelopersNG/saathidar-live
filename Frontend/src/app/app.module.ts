import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterModule,Routes } from '@angular/router';
import { SearchComponent } from './modules/member/components/search/search.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { NgxNavigationWithDataComponent } from 'ngx-navigation-with-data';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MembersearchComponent } from './modules/searchMember/component/membersearch/membersearch.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { AdminDashboardComponent } from './modules/admin/component/admin-dashboard/admin-dashboard.component';
import { DashboardComponent } from './modules/admin/component/dashboard/dashboard.component';
import { AdminHeaderComponent } from './modules/admin/component/admin-header/admin-header.component';
import { AdminFooterComponent } from './modules/admin/component/admin-footer/admin-footer.component';
import { AllCountMatchesComponent } from './modules/admin/component/all-count-matches/all-count-matches.component';
import { UpdatePremiumComponent } from './modules/admin/component/update-premium/update-premium.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { MemberProfileComponent } from './components/member-profile/member-profile.component';
import { HelpMobileComponent } from './components/help-mobile/help-mobile.component';
import { TermConditionMobileComponent } from './components/term-condition-mobile/term-condition-mobile.component';
import { PrivacyPolicyMobileComponent } from './components/privacy-policy-mobile/privacy-policy-mobile.component';



@NgModule({
  declarations: [
    AppComponent,
    SearchComponent, 
    LandingPageComponent,UpdatePremiumComponent, MembersearchComponent, AdminLoginComponent, AdminDashboardComponent, DashboardComponent, AdminHeaderComponent, AdminFooterComponent, AllCountMatchesComponent, MemberProfileComponent, HelpMobileComponent, TermConditionMobileComponent, PrivacyPolicyMobileComponent,
  ],
  imports: [
    NgMultiSelectDropDownModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
  ],
  // providers: [ SearchComponent,NgxNavigationWithDataComponent,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  providers: [ SearchComponent,NgxNavigationWithDataComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

