import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchRoutingModule } from './search-routing.module';
import { SearchDashboardComponent } from './components/search-dashboard/search-dashboard.component';
import { HeaderComponent } from '../member/components/header/header.component';
import { FooterComponent } from '../member/components/footer/footer.component';
import { HeaderSerachComponent } from './components/header-serach/header-serach.component';
import { FooterSearchComponent } from './components/footer-search/footer-search.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MatchesComponent } from './components/matches/matches.component';
import { NewMatchesComponent } from './components/new-matches/new-matches.component';
import { MyMatchesComponent } from './components/my-matches/my-matches.component';
import { TodaysMatchesComponent } from './components/todays-matches/todays-matches.component';
import { RefineSearchComponent } from './components/refine-search/refine-search.component';
import { ShortlistComponent } from './components/shortlist/shortlist.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ViewComponent } from './components/view/view.component';

@NgModule({
  declarations: [
    SearchDashboardComponent,
    HeaderSerachComponent,
    FooterSearchComponent,
    SidebarComponent,
    MatchesComponent,
    NewMatchesComponent,
    MyMatchesComponent,
    TodaysMatchesComponent,
    RefineSearchComponent,
    ShortlistComponent,
    ViewComponent
  ],
  imports: [
    CommonModule,
    SearchRoutingModule,
    MatProgressSpinnerModule
  ]
})
export class SearchModule { }
