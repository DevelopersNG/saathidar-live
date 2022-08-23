import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberDashboardComponent } from './components/member-dashboard/member-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { MyPhotosComponent } from './components/my-photos/my-photos.component';
import { PartnerPreferenceComponent } from './components/partner-preference/partner-preference.component';
import { UpdateMemberComponent } from './components/update-member/update-member.component';
import { PlansDetailsComponent } from './components/plans-details/plans-details.component';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import { UpdgradeNowComponent } from './components/updgrade-now/updgrade-now.component';
import { HoroscopeComponent } from './components/horoscope/horoscope.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { PaymentComponent } from './components/payment/payment.component';
import { AccountComponent } from './components/account/account.component';
import { ContactComponent } from './components/contact/contact.component';
import { HideDeleteProfileComponent } from './components/hide-delete-profile/hide-delete-profile.component';
import { AlbumComponent } from './components/album/album.component';
import { MultipalpicComponent } from './components/multipalpic/multipalpic.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileAlldetailsComponent } from './components/profile-alldetails/profile-alldetails.component';

@NgModule({
  declarations: [
    MemberDashboardComponent,
    HeaderComponent,
    FooterComponent,
    DashboardComponent,
    MyProfileComponent,
    MyPhotosComponent,
    PartnerPreferenceComponent,
    UpdateMemberComponent,
    PlansDetailsComponent,
    AccountDetailsComponent,
    UpdgradeNowComponent,
    HoroscopeComponent,
    PaymentComponent,
    AccountComponent,
    ContactComponent,
    HideDeleteProfileComponent,
    AlbumComponent,
    MultipalpicComponent,
    ProfileComponent,
    ProfileAlldetailsComponent
  ],
  imports: [
    CommonModule,
    MemberRoutingModule,
    AngularEditorModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    NgMultiSelectDropDownModule.forRoot(),
    
   
  ]
})
export class MemberModule { }
