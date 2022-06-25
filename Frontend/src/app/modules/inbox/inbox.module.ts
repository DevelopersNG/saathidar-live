import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InboxRoutingModule } from './inbox-routing.module';
import { InvitationsComponent } from './components/invitations/invitations.component';
import { AcceptedComponent } from './components/accepted/accepted.component';
import { RequestsComponent } from './components/requests/requests.component';
import { SentComponent } from './components/sent/sent.component';
import { DeletedComponent } from './components/deleted/deleted.component';
import { InboxDashboardComponent } from './components/inbox-dashboard/inbox-dashboard.component';
import { InboxHeaderComponent } from './components/inbox-header/inbox-header.component';
import { InboxFooterComponent } from './components/inbox-footer/inbox-footer.component';
import { InboxSidebarComponent } from './components/inbox-sidebar/inbox-sidebar.component';


@NgModule({
  declarations: [
    InvitationsComponent,
    AcceptedComponent,
    RequestsComponent,
    SentComponent,
    DeletedComponent,
    InboxDashboardComponent,
    InboxHeaderComponent,
    InboxFooterComponent,
    InboxSidebarComponent
  ],
  imports: [
    CommonModule,
    InboxRoutingModule
  ]
})
export class InboxModule { }
