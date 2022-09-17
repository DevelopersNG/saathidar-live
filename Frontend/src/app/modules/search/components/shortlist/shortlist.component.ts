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
  imageURL='http://103.174.102.195:8080'
  gender:any;
  genderImageURL='/saathidaar/assets/img'
  constructor( private router: Router,private matches_service:MatchesService
    ) { }
    // demoUrls : string[] = [];
    demoUrlsActive : string[] = [];
    demoUrls:any;
    member_id:any;
  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
   
     // start
     this.gender = localStorage.getItem('login_credentials_gender');
  
     this.gender = localStorage.getItem('login_credentials_gender');
    if(this.gender=='male' || this.gender=='Male'){
      this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
    }
    if(this.gender=='female' || this.gender=='Female'){
      this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
    }
    this.getShortListMember(this.member_id);
    // alert(JSON.stringify(this.genderImageURL))
    // end
    $(document).ready(function(){
      $('img').click(function(){
      $('img').removeClass("imageb");
      $(this).addClass("imageb");
    });
    });
  }
    // ****************************************** STart
    profileIMags:any;
    demoUrlsModal:any;
    callIMageForMemberID(member_id:any){
      this.profileIMags=null;
      this.demoUrlsModal=null;
      this.seturl=null;
      this.matchesService.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        if(results.data.member_images!='undefined'){
        this.profileIMags=results.data[0].member_images;
        this.demoUrlsModal=results.data;
      }
       });
    }
    // *********************************************** End
  loader = false;
  shortlistDetails:any;
  filterAll()
  {
    this.getShortListMember(this.member_id);
  }
  getShortListMember(member_id:any){
    // this.loader = true;
    this.matches_service.getShortListMember(member_id)
    .subscribe(
      (results: any) => {
        // alert(JSON.stringify(results))
        this.shortlistDetails =results.data;
        // alert(JSON.stringify(results.data) )
        // this.loader = false;
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
  // alert(JSON.stringify(member_to_id))
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
        // alert(JSON.stringify(data))
        // if requests are send successfully then call member details again
        if (results != null) {
          this.callSearchDetails(this.member_id);
        }
      },
      (      error: any) => {
        this.loader = false;
        console.log(error);
      });

}


seturl:any;
loadTIme:boolean=false;
change(){
  this.loadTIme=false;
}
showimage(url:any)
{
 this.seturl=url;
 this.loadTIme=true;
}

check(val:any){
// alert(val)

  }

   
}
