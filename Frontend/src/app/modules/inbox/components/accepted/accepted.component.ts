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

  constructor(private router: Router,private inbox_service:InboxService) { }
  member_id:any;
  member_email_id:any;
  ngOnInit(): void { 
  this.member_id=localStorage.getItem('login_credentials');

  this.getAcceptedDetails(this.member_id);
  }
 
  acceptedDetails:any=0;
  
singleViewMemberDetails(member_id:string){
  this.router.navigate(['/members/profile/'+member_id]);
}

  getAcceptedDetails(member_id:any){
  this.inbox_service.getAcceptedDetails(member_id)
  .subscribe(
    (results: any) => {
      this.acceptedDetails =results.data;
    },
    (      error: any) => {
      console.log(error);
    });
}

}
