import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxService } from '../../Services/inbox.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-invitations',
  templateUrl: './invitations.component.html',
  styleUrls: ['./invitations.component.css']
})
export class InvitationsComponent implements OnInit {

  constructor(private router: Router,private inbox_service:InboxService)
  {}
  // demoUrls : string[] = [];

  demoUrls:any;
  demoUrlsActive : string[] = [];
  loader = false;
  invitationsDetails:any=0; 
  member_id:any;
  seturl:any;
  loadTIme:boolean=false;
  imageURL='http://103.174.102.195:8080'
  genderImageURL='/saathidaar/assets/img'
  gender:any;
  
    ngOnInit(): void { 
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
  
  this.getInvitationsDetails(this.member_id);
  // this.getIMagesForMembers(this.member_id);
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
      this.inbox_service.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        if(results.data.member_images!='undefined'){
        this.profileIMags=results.data[0].member_images;
        this.demoUrlsModal=results.data;
      }
       });
    }
  
    // *********************************************** End
    


 


filterAll()
{
  this.getInvitationsDetails(this.member_id);
}

getInvitationsDetails(member_id:any){
  this.loader = true;
  this.inbox_service.getInvitationsDetails(member_id)
  .subscribe(
    (results: any) => {
      this.invitationsDetails =results.data;
      // alert(JSON.stringify(results))

      this.loader = false;
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
acceptMemberRequests(memberID:string){
  const data={
    request_from_id:memberID,
    request_to_id: this.member_id,
    request_status:"Accepted"
  }
  // alert(JSON.stringify(memberID.length))

  this.inbox_service.acceptMemberRequests(data)
  .subscribe(
    (results: any) => {
    
      this.getInvitationsDetails(this.member_id);
    },
    (error: any) => {
      console.log(error);
      window.location.reload();
    });
}
deletedMemberRequests(memberID:string){
  const data={
    request_from_id:memberID,
    request_to_id: this.member_id,
    request_status:"Rejected"
  }
  // alert(JSON.stringify(data))

  this.inbox_service.acceptMemberRequests(data)
  .subscribe(
    (results: any) => {                                                                     
      this.getInvitationsDetails(this.member_id);
    },
    (error: any) => {
      console.log(error);
      window.location.reload();

    });
}

// ************************************modal**************************


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
  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.inbox_service.sentShortListData(data)
      .subscribe(
        results => {
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
        },
        error => {
          console.log(error);
          window.location.reload();

        });
  }



}