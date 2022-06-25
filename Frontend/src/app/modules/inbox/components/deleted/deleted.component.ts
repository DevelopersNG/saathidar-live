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
  ngOnInit(): void { 
  this.member_id=localStorage.getItem('login_credentials');
  this.getRejectedDetails(this.member_id);
}

loader = false;
rejectedDetails:any=0; 

getRejectedDetails(member_id:any){
  this.loader = true;
  this.inbox_service.getdeletedMemberRequests(member_id)
  .subscribe(
    (results: any) => {
      this.rejectedDetails =results.data;
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
