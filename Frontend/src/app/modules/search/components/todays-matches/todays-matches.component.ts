import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import * as $ from 'jquery';
import { MatchesService } from '../../services/matches.service';

@Component({
  selector: 'app-todays-matches',
  templateUrl: './todays-matches.component.html',
  styleUrls: ['./todays-matches.component.css']
})
export class TodaysMatchesComponent implements OnInit {
  loader = false;
  searchMemberDetails: any;
  searchByJSONDetails: any;
  member_id: any;  
  memberIDs: any;

  constructor(private router:Router,private matchesService:MatchesService) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.callSearchDetails(this.member_id); 
  }

  
  
  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }

  callSearchDetails(member_id: any) {
    this.loader = true;
    this.matchesService.searchTodayMatchesAllMember( member_id)
      .subscribe(
        results => {

          this.searchMemberDetails = results.data;
         
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

  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.matchesService.sentShortListData(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if (results != null) {
            this.callSearchDetails(this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });
  }

  sentRequests(member_to_id: string) {
    const data = {
      request_from_id: this.member_id,
      request_to_id: member_to_id,
      request_status: "Pending"
    }

// alert(JSON.stringify(data))

    this.matchesService.sentRequests(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if (results != null) {
            this.callSearchDetails(this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });

    // alert(JSON.stringify(data));
  }

  opendetails()
  {
    this.router.navigate(['members/profile/1']);
  }

}
