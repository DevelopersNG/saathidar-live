import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcceptedComponent } from './components/accepted/accepted.component';
import { DeletedComponent } from './components/deleted/deleted.component';
import { InboxDashboardComponent } from './components/inbox-dashboard/inbox-dashboard.component';
import { InvitationsComponent } from './components/invitations/invitations.component';
import { RequestsComponent } from './components/requests/requests.component';
import { SentComponent } from './components/sent/sent.component';


const routes: Routes = [
  {
    path: '', component: InboxDashboardComponent,
    children: [
      { path: 'invitations', component: InvitationsComponent},
      { path: 'accepted', component: AcceptedComponent},
      { path: 'requests', component: RequestsComponent},
      { path: 'sent', component: SentComponent},
      { path: 'deleted', component: DeletedComponent},
      {path:'', redirectTo:'/inbox/invitations',pathMatch:'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InboxRoutingModule { }
