import { NgModule } from '@angular/core';

import { RoutingModule } from './-routing.module';
import { UpdatePremiumComponent } from './admin/component/update-premium/update-premium.component';
import { MemberDetailsComponent } from './admin/component/member-details/member-details.component';
import { MemberDetailsShowComponent } from './admin/component/member-details-show/member-details-show.component';


@NgModule({
  declarations: [
    UpdatePremiumComponent,
    MemberDetailsComponent,
    MemberDetailsShowComponent
  ],
  imports: [
    RoutingModule
  ]
})
export class Module { }
