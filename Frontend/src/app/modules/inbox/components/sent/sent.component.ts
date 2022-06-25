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

    member_id:any;
  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
    this.getSentInvitations(this.member_id);
  }

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
        this.sentDetails =results.data;
        // alert(JSON.stringify(this.sentDetails) )
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
}
