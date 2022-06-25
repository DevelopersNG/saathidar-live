import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchesService } from '../../services/matches.service';

@Component({
  selector: 'app-shortlist',
  templateUrl: './shortlist.component.html',
  styleUrls: ['./shortlist.component.css']
})
export class ShortlistComponent implements OnInit {
 
  searchMemberDetails: any;
  searchByJSONDetails: any;
  
  memberIDs: any;
  matchesService: any;
  callSearchDetails: any;

  constructor( private router: Router,private matches_service:MatchesService
    ) { }

    member_id:any;
  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
    this.getShortListMember(this.member_id);
  }

  loader = false;
  shortlistDetails:any;

  filterAll()
  {
    this.getShortListMember(this.member_id);
  }

  getShortListMember(member_id:any){
    this.loader = true;
    this.matches_service.getShortListMember(member_id)
    .subscribe(
      (results: any) => {
        this.shortlistDetails =results.data;
        // alert(JSON.stringify(this.sentDetails) )
        this.loader = false;
      },
      (      error: any) => {
        this.loader = false;
        console.log(error);
      });
  }

  singleViewMemberDetails(member_id:string){
    this.router.navigate(['/members/profile/'+member_id]);
  }

removeToShortList(member_to_id:string){
  const data = {
    shortlist_from_id: this.member_id,
    shortlist_to_id: member_to_id,
    shortlist_status: "remove"
  }

  this.matches_service.removeShortListData(data)
    .subscribe(
      (results: any)  => {
        this.getShortListMember(this.member_id);
      },
      (      error: any) => {
        this.loader = false;
        console.log(error);
      });
}
// ******************************new*****************************
sentRequests(member_to_id: string) {
  const data = {
    request_from_id: this.member_id,
    request_to_id: member_to_id,
    request_status: "Pending"
  }

// alert(JSON.stringify(data))

  this.matchesService.sentRequests(data)
    .subscribe(
      (      results: null) => {
        // if requests are send successfully then call member details again
        if (results != null) {
          this.callSearchDetails(this.member_id);
        }
      },
      (      error: any) => {
        this.loader = false;
        console.log(error);
      });

  // alert(JSON.stringify(data));
}

}
