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



@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,   
    LandingPageComponent, MembersearchComponent,
 

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
    MatProgressSpinnerModule
 
    
  ],
  providers: [ SearchComponent,NgxNavigationWithDataComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

