import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as $ from 'jquery';
import { MatchesService } from '../../services/matches.service';

@Component({

  selector: 'app-new-matches',
  templateUrl: './new-matches.component.html',
  styleUrls: ['./new-matches.component.css']
})
export class NewMatchesComponent implements OnInit {
  
  loader = false;
  searchMemberDetails: any;
  searchByJSONDetails: any;
  member_id: any;  
  memberIDs: any;
  upgrade=false
  blockMasseage=false
  sendRequest=false
  showBlankPageImage=false
  constructor( private router:Router,private matchesService:MatchesService) { 
  }
  // demoUrls : string[] = [];
  demoUrlsActive : string[] = [];
  demoUrls:any;
  gender:any;
  imageURL='http://103.174.102.195:8080'
  genderImageURL='/saathidaar/assets/img'
  ngOnInit(): void {
    // alert(window.location.origin)
    $(document).ready(function(){
      $('img').click(function(){
        $('img').removeClass("imageb");
        $(this).addClass("imageb");
    });
    });
    this.member_id = localStorage.getItem('login_credentials');
    // start
    this.gender = localStorage.getItem('login_credentials_gender');
  
    if(this.gender=='male' || this.gender=='Male'){
      this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
    }
    if(this.gender=='female' || this.gender=='Female'){
      this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
    }
    // end
    this.callSearchDetails(this.member_id);  
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
  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }
  callSearchDetails(member_id: any) {
    this.loader = true;
    this.matchesService.searchNewMatchesAllMember(member_id)
      .subscribe(
        results => {
          // alert(JSON.stringify(results))
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

  addToShortList(member_to_id: string,my_premium_status:string) {
    if(my_premium_status == '2')
    {
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
          window.location.reload();
        });
        }else
        {
          this.upgrade=true;
        }
  }
  sentRequests(member_to_id: string,my_premium_status:string) {
    if(my_premium_status == '2')
    {
    const data = {
      request_from_id: this.member_id,
      request_to_id: member_to_id,
      request_status: "Pending"
    }
    this.matchesService.sentRequests(data)
      .subscribe(
        results => {
    // alert(JSON.stringify(results))
          // if requests are send successfully then call member details again
          if (results != null) {
            this.callSearchDetails(this.member_id);
          }

        },
        error => {
          this.loader = false;
          console.log(error);
          window.location.reload();
        });
      }else
      {
        this.sendRequest=true;
      }

    // alert(JSON.stringify(data));
  }

  opendetails()
  {
    this.router.navigate(['members/profile/1']);
  }

  // ***************************************new line modal**********************************

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

  blockmemberID(member_to_id: string,my_premium_status:string) {
    if(my_premium_status == '2')
    {
    const data = {
      request_from_id:member_to_id,
      request_to_id:this.member_id,
      block_by_id:this.member_id,
      block_status:"Block"
    }
    this.matchesService.blockmember(data)
      .subscribe(
        results => {
  //  alert(JSON.stringify(data))

          // alert(JSON.stringify(results))
          window.location.reload();
        },
        error => {
          console.log(error);
         
        });
      }else
      {
        this.blockMasseage=true;
      }
  }


// photoNotFound(member_id:any)
// {
//   this.matchesService.photoNotFound(member_id)
//   .subscribe(
//     results => {
  

//     },
//     error => {
//       console.log(error);
//     });
// }
 
}
