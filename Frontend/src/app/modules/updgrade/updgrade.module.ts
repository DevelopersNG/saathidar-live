import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UpdgradeRoutingModule } from './updgrade-routing.module';
import { UpdgradeComponent } from './component/updgrade/updgrade.component';
import { UpdgradeDashboardComponent } from './component/updgrade-dashboard/updgrade-dashboard.component';
import { UpdgradeHeaderComponent } from './component/updgrade-header/updgrade-header.component';
import { UpdgradeFooterComponent } from './component/updgrade-footer/updgrade-footer.component';


@NgModule({
  declarations: [
    UpdgradeComponent,
    UpdgradeDashboardComponent,
    UpdgradeHeaderComponent,
    UpdgradeFooterComponent
  ],
  imports: [
    CommonModule,
    UpdgradeRoutingModule
  ]
})
export class UpdgradeModule { }
