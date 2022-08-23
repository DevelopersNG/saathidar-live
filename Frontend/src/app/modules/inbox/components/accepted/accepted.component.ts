import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxService } from '../../Services/inbox.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-accepted',
  templateUrl: './accepted.component.html',
  styleUrls: ['./accepted.component.css']
})
export class AcceptedComponent implements OnInit {

  constructor(private router: Router,private inbox_service:InboxService,) { }
  member_id:any;
  member_email_id:any;
  // demoUrls : string[] = [];
  demoUrlsActive : string[] = [];
  demoUrls:any;
 
  acceptedDetails:any=0;
  imageURL='http://103.150.186.33:8080'
  gender:any;
  genderImageURL='/saathidaar/assets/img'
  ngOnInit(): void { 
  this.member_id=localStorage.getItem('login_credentials');
  // this.callSearchDetails(this.member_id); 
   // start
 this.gender = localStorage.getItem('login_credentials_gender');

 if(this.gender=='male' || this.gender=='Male'){
  this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
}
if(this.gender=='female' || this.gender=='Female'){
  this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
}
// end
 
  this.getAcceptedDetails(this.member_id);
  $(document).ready(function(){
    $('img').click(function(){
     $('img').removeClass("imageb");
      $(this).addClass("imageb");
      
  });
  });
  }

  // ****************************************** STart
profileIMags:any;
callIMageForMemberID(member_id:any){
  this.inbox_service.getDataImageDataByURL(member_id)
  .subscribe((results : any) => {
   
    this.profileIMags=results.data[0].member_images;
    this.demoUrls=results.data;
    
      // this.demoUrls.push(results);
    // alert(this.demoUrls)
   });
}
// *********************************************** End

singleViewMemberDetails(member_id:string){
  this.router.navigate(['/members/profile/'+member_id]);
}

  getAcceptedDetails(member_id:any){
  this.inbox_service.getAcceptedDetails(member_id)
  .subscribe(
    (results: any) => {
      // alert(JSON.stringify(results))

      this.acceptedDetails =results.data;
    // this.acceptedDetails === "my_accepted" || this.acceptedDetails[0] !== "my_accepted"

    },
    (      error: any) => {
      console.log(error);
    });
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

  // ******************************new line shortlist***********************************

  

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
          window.location.reload();

        },
        error => {
          // this.loader = false;
          console.log(error);
          // window.location.reload();

        });
  }
  deletedMemberRequests(memberID:string){
    const data={
      request_from_id:memberID,
      request_to_id: this.member_id,
      request_status:"Rejected"
    }
  
    this.inbox_service.acceptMemberRequests(data)
    .subscribe(
      (results: any) => {                                                                     
        // this.getInvitationsDetails(this.member_id);
        window.location.reload();

      },
      (error: any) => {
        console.log(error);
        // window.location.reload();

      });
  }

  // ************************************block*************************


  blockmemberID(member_to_id: string) {
    const data = {
      request_from_id:member_to_id,
      request_to_id:this.member_id,
      block_by_id:member_to_id,
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
          // window.location.reload();

        });
  }
}
