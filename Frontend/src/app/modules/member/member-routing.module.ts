import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/search/search.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MemberDashboardComponent } from './components/member-dashboard/member-dashboard.component';
import { MyPhotosComponent } from './components/my-photos/my-photos.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { PartnerPreferenceComponent } from './components/partner-preference/partner-preference.component';
import { PlansDetailsComponent } from './components/plans-details/plans-details.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UpdateMemberComponent } from './components/update-member/update-member.component';
import { UpdgradeNowComponent } from './components/updgrade-now/updgrade-now.component';
import { HoroscopeComponent } from './components/horoscope/horoscope.component';
import { PaymentComponent } from './components/payment/payment.component';
import { AccountComponent } from './components/account/account.component';
import { ContactComponent } from './components/contact/contact.component';
import { HideDeleteProfileComponent } from './components/hide-delete-profile/hide-delete-profile.component';
import { AlbumComponent } from './components/album/album.component';

const routes: Routes = [
  {
    path: '', component: MemberDashboardComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'search', component: SearchComponent },
      // { path: 'payment/:plan_name/:plan_amount', component: PaymentComponent },
      { path: 'payment/:plan_name', component: PaymentComponent },
      { path: 'profile/:member_id', component: ProfileComponent },
      { path: 'my-profile', component: MyProfileComponent },
      { path: 'my-profile/edit', component: UpdateMemberComponent },
      { path: 'my-photos', component: MyPhotosComponent },
      { path: 'partner-preference', component: PartnerPreferenceComponent },
      { path: 'plans', component: PlansDetailsComponent},
      { path: 'upgrade-now', component: UpdgradeNowComponent },
      { path: 'astro', component: HoroscopeComponent },
      { path: 'account', component: AccountComponent},
      { path: 'contact', component: ContactComponent},
      { path: 'hide-delete-profile', component: HideDeleteProfileComponent},
      { path: 'album', component:AlbumComponent},
      {path:'', redirectTo:'/members/search',pathMatch:'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
