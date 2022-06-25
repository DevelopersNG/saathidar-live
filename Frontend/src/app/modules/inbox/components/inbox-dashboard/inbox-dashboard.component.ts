import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inbox-dashboard',
  templateUrl: './inbox-dashboard.component.html',
  styleUrls: ['./inbox-dashboard.component.css']
})
export class InboxDashboardComponent implements OnInit {

  member_id:any;
  member_email_id:any;

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
    if(this.member_id!=null && this.member_id!=''){
      this.member_id=localStorage.getItem('login_credentials');
      this.member_email_id=localStorage.getItem('login_credentials_email');
    }else{
      this.router.navigate(['']);
    }
  }

}
