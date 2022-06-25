import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatchesComponent } from './components/matches/matches.component';
import { NewMatchesComponent } from './components/new-matches/new-matches.component';
import { MyMatchesComponent } from './components/my-matches/my-matches.component';
import { TodaysMatchesComponent } from './components/todays-matches/todays-matches.component';
import { SearchDashboardComponent } from './components/search-dashboard/search-dashboard.component';
import { RefineSearchComponent }from './components/refine-search/refine-search.component';
import { ShortlistComponent }from './components/shortlist/shortlist.component';
import { ViewComponent } from './components/view/view.component';

const routes: Routes = [
  {
    path: '', component: SearchDashboardComponent,
    children: [
      { path: 'members', component: MatchesComponent },
      { path: 'new', component: NewMatchesComponent },
      { path: 'my', component: MyMatchesComponent },
      { path: 'todays', component: TodaysMatchesComponent },
      { path: 'shortlists', component: ShortlistComponent },
      { path: 'view', component: ViewComponent },
      { path: 'refine-search/:titlename/:searchname/:id', component: RefineSearchComponent },
      {path:'', redirectTo:'/search/members',pathMatch:'full'},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule { }
