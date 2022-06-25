import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
}) 
export class DashboardComponent implements OnInit {
  member_id:any;
 viewAllCount:any;
 searchMemberDetails: any;
 premiumMemberDetails: any;
 memberIDs: any;
 loader = false;
 
  constructor(private router:Router,
    private searchService: SearchService) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.getAllCount(this.member_id); 
  
    this.callSearchDetails(this.member_id);  
    this.callNewPreiumMatchesDetails();
     
  }
 

 getAllCount(member_id: any) {
  this.searchService.getAllCount(member_id)
  .subscribe(
    results => {
      this.viewAllCount=results;
    },
    error => {
      console.log(error);
    });
}


callAcceptRequest(){
  this.router.navigate(['/inbox/accepted']);
}

callSendRequest(){
  this.router.navigate(['/inbox/sent']);
}
// **************************new***************************
singleViewMemberDetails(member_id: string) {
  this.router.navigate(['/members/profile/' + member_id]);

}
callSearchDetails(member_id: any) {
  this.loader = true;
  this.searchService.searchNewMatchesAllMember( member_id)
    .subscribe(
      results => {
        this.searchMemberDetails = results.data;
        // this is for next and previous- start
        this.memberIDs = [];
        for (var i = 0; i < this.searchMemberDetails.length; i++) {
          this.memberIDs.push(this.searchMemberDetails[i]['member_id']);
        }
        sessionStorage.setItem("value", JSON.stringify(this.memberIDs));
        // this is for next and previous- end  
        this.loader = false;
      },
      error => {
        this.loader = false;
        console.log(error);
      });
}

callNewPreiumMatchesDetails(){
  this.loader = true;
  this.searchService.callNewPreiumMatchesDetails(this.member_id)
    .subscribe(
      results => {
    //  alert(results.data)
        this.premiumMemberDetails = results.data;
        // this is for next and previous- start
        this.memberIDs = [];
        for (var i = 0; i < this.premiumMemberDetails.length; i++) {
          this.memberIDs.push(this.premiumMemberDetails[i]['member_id']);
        }
        sessionStorage.setItem("value", JSON.stringify(this.memberIDs));
        // this is for next and previous- end  
        this.loader = false;
      },
      error => {
        this.loader = false;
        console.log(error);
      });
}
}