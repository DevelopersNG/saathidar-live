import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatchesService } from '../../services/matches.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  loader = false;
  searchMemberDetails: any;
  recentVisitor:any; 
  searchByJSONDetails: any;
  member_id: any;  
  memberIDs: any;
  viewRecently:any;
  gender:any;
  imageURL='http://103.174.102.195:8080'
  demoUrls:any;
profileIMags:any;
  genderImageURL='/saathidaar/assets/img'
  constructor(private router:Router ,private matchesService:MatchesService) { }
  ngOnInit(): void {
   this.member_id = localStorage.getItem('login_credentials');
    this.callRecentVisitorhDetails(this.member_id); 
    this.viewRecentlyDetails(this.member_id); 
      // start
      this.gender = localStorage.getItem('login_credentials_gender');
 
      if(this.gender=='male' || this.gender=='Male'){
        this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
      }
      if(this.gender=='female' || this.gender=='Female'){
        this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
      }
      // end
  }
  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }

  callRecentVisitorhDetails(member_id: any) {
    this.loader = true;
    this.matchesService.recentVisitor(member_id)
      .subscribe(
        results => {
// alert(JSON.stringify(results.data))
          this.recentVisitor = results.data;
          // this is for next and previous- start
          this.memberIDs = [];
          for (var i = 0; i < this.recentVisitor.length; i++) {
            this.memberIDs.push(this.recentVisitor[i]['member_id']);
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

      
      viewRecentlyDetails(member_id: any) {
    this.loader = true;
    this.matchesService.viewRecently(member_id)
      .subscribe(
        results => {

          this.viewRecently = results.data;
          // alert(JSON.stringify(results.data))
          // this is for next and previous- start
          this.memberIDs = [];
          for (var i = 0; i < this.viewRecently.length; i++) {
            this.memberIDs.push(this.viewRecently[i]['member_id']);
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


  // ****************************************get images******************************
    // ****************************************** STart

getIMagesForMembers(member_id: any) {
  this.matchesService.getData(member_id)
  .subscribe((results : any) => {
    // alert(JSON.stringify(results.data))
    this.profileIMags=results.data[2].member_images;
    this.demoUrls=results.data;
      // this.demoUrls.push(results);
    // alert(this.demoUrls)
   });
}
callIMageForMemberID(member_id:any){
  this.getIMagesForMembers(member_id);
}
// *********************************************** End


}
