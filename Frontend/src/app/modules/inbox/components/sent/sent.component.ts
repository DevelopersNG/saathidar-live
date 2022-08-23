import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxService } from '../../Services/inbox.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
 
@Component({
  selector: 'app-sent',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent implements OnInit {

  constructor( private router: Router,private inbox_service:InboxService
    ) { }
    // demoUrls : string[] = [];
    demoUrlsActive : string[] = [];
    demoUrls:any;
    member_id:any;
    imageURL='http://103.150.186.33:8080'
    gender:any;
    genderImageURL='/saathidaar/assets/img'
  ngOnInit(): void {
    // this.getInvitationsdelete(this.member_id);
    $(document).ready(function(){
      $('img').click(function(){
     // alert(JSON.stringify($(this)))
        $('img').removeClass("imageb");
        $(this).addClass("imageb");
        
    });
    });
    
    this.member_id=localStorage.getItem('login_credentials');
    this.getSentInvitations(this.member_id);
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
 
    // ****************************************** STart
    profileIMags:any;
    demoUrlsModal:any;
    callIMageForMemberID(member_id:any){
  
      this.profileIMags=null;
      this.demoUrlsModal=null;
      this.seturl=null;
      this.inbox_service.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        if(results.data.member_images!='undefined'){
        this.profileIMags=results.data[0].member_images;
        this.demoUrlsModal=results.data;
      }
       });
    }
  
    // *********************************************** End
    

  loader = false;
  sentDetails:any=0;

  filterAll()
  {
    this.getSentInvitations(this.member_id);
  }


  getSentInvitations(member_id:any){
    this.loader = true;
    this.inbox_service.getSentDetails(member_id)
    .subscribe(
      (results: any) => {
        // alert(JSON.stringify(results.data))

        this.sentDetails =results.data;
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
  // ************************************modal**************************

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

  // *******************************delete member ************************

  getInvitationsdelete(memberID:string){
    const data={
      request_from_id:memberID,
      request_to_id: this.member_id,
      request_status:"Rejected"
    }
  
    this.inbox_service.acceptMemberRequests(data)
    .subscribe(
      (results: any) => {
        this.getInvitationsdelete(this.member_id);
      },
      (error: any) => {
        console.log(error);
        window.location.reload();

      });
  }
  
 

  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.inbox_service.sentShortListData(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          // if (results != null) {
          //   this.callSearchDetails(this.member_id);
          // }
        },
        error => {
          // this.loader = false;
          console.log(error);
          window.location.reload();

        });
  }

  deletedMemberRequests(memberID:string){
    const data={
      request_from_id:memberID,
      request_to_id: this.member_id,
      request_status:"Canceled"
    }
  
    this.inbox_service.acceptMemberRequests(data)
    .subscribe(
      (results: any) => {        
    // alert(JSON.stringify(results))
    window.location.reload();
      },
      (error: any) => {
        console.log(error);
        window.location.reload();

      });
  }
  // ************************************block*************************

  blockmemberID(member_to_id: string) {

    const data = {
      request_from_id:member_to_id,
      request_to_id:this.member_id,
      block_by_id:this.member_id,
      block_status:"Block"
    }
   
    this.inbox_service.blockmember(data)
      .subscribe(
        results => {
          // alert(JSON.stringify(results))
          window.location.reload();
        },
        error => {
          console.log(error);
          window.location.reload();

        });
  }


}
