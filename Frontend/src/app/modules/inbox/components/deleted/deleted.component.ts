import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxService } from '../../Services/inbox.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-deleted',
  templateUrl: './deleted.component.html',
  styleUrls: ['./deleted.component.css']
})
export class DeletedComponent implements OnInit {

  
  constructor(private router: Router,private inbox_service:InboxService){}
  member_id:any;
  member_email_id:any;
  // demoUrls : string[] = [];
  demoUrlsActive : string[] = [];
  demoUrls:any;
   
loader = false;
rejectedDetails:any=0; 
imageURL='http://103.174.102.195:8080'
gender:any;
genderImageURL='/saathidaar/assets/img'
  
  ngOnInit(): void { 
  this.member_id=localStorage.getItem('login_credentials');
  this.getRejectedDetails(this.member_id);

  this.member_id=localStorage.getItem('login_credentials');
   // start
 this.gender = localStorage.getItem('login_credentials_gender');

 if(this.gender=='male' || this.gender=='Male'){
  this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
}
if(this.gender=='female' || this.gender=='Female'){
  this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
}
// end

  
  $(document).ready(function(){
    $('img').click(function(){

      // alert(JSON.stringify($(this)))

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
     this.inbox_service.getDataImageDataByURL(member_id)
     .subscribe((results : any) => {
       if(results.data.member_images!='undefined'){
       this.profileIMags=results.data[0].member_images;
       this.demoUrlsModal=results.data;
     }
      });
   }
   // *********************************************** End
   includes:string;
   button=true;
getRejectedDetails(member_id:any){
  this.loader = true;
  this.inbox_service.getdeletedMemberRequests(member_id)
  .subscribe(
    (results: any) => {
      // alert(JSON.stringify(results))
// Canceled Pending Accepted
      this.rejectedDetails=results.data;
      // alert(JSON.stringify(this.rejectedDetails[0].request_message))
  //     if(this.rejectedDetails.request_message.includes("cancelled") || this.rejectedDetails.request_message.includes("declined")) 
  // {
  // this.button=false;
  // }
      this.loader = false;
      // window.location.reload();

    },
    (      error: any) => {
      this.loader = false;
      console.log(error);
      window.location.reload();

    });
}

singleViewMemberDetails(member_id:string){
  this.router.navigate(['/members/profile/'+member_id]);
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
  acceptMemberRequests(memberID:string){
    const data={
      request_from_id:memberID,
      request_to_id: this.member_id,
      request_status:"Accepted"
    }
    this.inbox_service.acceptMemberRequests(data)
    .subscribe(
      (results: any) => {
    // alert(JSON.stringify(results))
        window.location.reload();
        // this.getInvitationsDetails(this.member_id);
      },
      (error: any) => {
        console.log(error);
        // window.location.reload();
      });
  }
  
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
          window.location.reload();
          // alert(JSON.stringify(results))
        },
        error => {
          console.log(error);
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
          window.location.reload();

          //  alert(JSON.stringify(results))
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

  
}
