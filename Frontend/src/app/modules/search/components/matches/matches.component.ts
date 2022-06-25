import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { SharingService } from '../../services/sharing.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {
  // searchMemberDetailsView(data: string) {
  //   this.searchMemberDetails = JSON.stringify(data);
  // }
  loader = false;
  searchMemberDetails: any;
  searchByJSONDetails: any;
  member_id: any;

  constructor(private landingPageServices: LandingPageService,
    private router: Router,
    private sharingService: SharingService) {
  }

  ngOnInit(): void {
    // this.data= localStorage.getItem('filter_data');
    // alert(localStorage.getItem("homepage_search_details"));
    this.searchByJSONDetails = localStorage.getItem("homepage_search_details");
    this.member_id = localStorage.getItem('login_credentials');
    this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
  }

  religionList: string;
  List: string;
  memberIDs: any;
    
 

  callSearchDetails(searchByJSONDetails: any, member_id: any) {
    this.loader = true;
    this.landingPageServices.searchMemberDetails(searchByJSONDetails, member_id)
      .subscribe(
        results => {
          this.searchMemberDetails = results;
          // this is for next and previous- start
          this.memberIDs = [];
          for (var i = 0; i < this.searchMemberDetails.length; i++) {
            this.memberIDs.push(this.searchMemberDetails[i]['member_id']);
          }
          // alert(this.memberIDs);
          // this.sharingService.sharingValue=this.memberIDs;  
          sessionStorage.setItem("value", JSON.stringify(this.memberIDs));
          // this is for next and previous- end  


          this.loader = false;
        },
        error => {
          this.loader = false;
          console.log(error);
        });
  }

  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }

  sentRequests(member_to_id: string) {
    const data = {
      request_from_id: this.member_id,
      request_to_id: member_to_id,
      request_status: "Pending"
    }

    this.landingPageServices.sentRequests(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if (results != null) {

            this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });

    // alert(JSON.stringify(data));
  }


  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.landingPageServices.sentShortListData(data)
      .subscribe(
        results => {
       
          // if requests are send successfully then call member details again
          if (results != null) {
             

            this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });
  }

}
