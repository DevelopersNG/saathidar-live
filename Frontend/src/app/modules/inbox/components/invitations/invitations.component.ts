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

    member_id:any;
  ngOnInit(): void { 
  this.member_id=localStorage.getItem('login_credentials');
  this.getInvitationsDetails(this.member_id);
}

loader = false;
invitationsDetails:any=0; 

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

acceptMemberRequests(memberID:string){
  const data={
    request_from_id:memberID,
    request_to_id: this.member_id,
    request_status:"Accepted"
  }

  this.inbox_service.acceptMemberRequests(data)
  .subscribe(
    (results: any) => {
      this.getInvitationsDetails(this.member_id);
    },
    (error: any) => {
      console.log(error);
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
      this.getInvitationsDetails(this.member_id);
    },
    (error: any) => {
      console.log(error);
    });
}



}
