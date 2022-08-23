import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoutingModule } from './-routing.module';
import { UpdatePremiumComponent } from './admin/component/update-premium/update-premium.component';
import { MemberDetailsComponent } from './admin/component/member-details/member-details.component';


@NgModule({
  declarations: [
    UpdatePremiumComponent,
    MemberDetailsComponent
  ],
  imports: [
    CommonModule,
    RoutingModule
  ]
})
export class Module { }
