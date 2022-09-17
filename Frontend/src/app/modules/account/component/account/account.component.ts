import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Accounts } from '../../model/accounts.model';
import { SettingService } from '../../services/setting.service';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  success_message:any;
  error_message:any;
  constructor( private router:Router,private settingsService:SettingService) { }

  member_id:any;
  member_email_id:any;

  ngOnInit(): void {
    // if(this.member_id!=null && this.member_id!=''){
      this.member_id=localStorage.getItem('login_credentials');
      this.member_email_id=localStorage.getItem('login_credentials_email');
    // }else{
    //   this.router.navigate(['']);
    // }

  }
 

  account:Accounts={
    oldPassword: '',
    newPassword: '',
    username: '',
    hide: '',
    unHide: '',
    oneWeek: '',
    twoWeek: '',
    oneMonth: '',
    twoMonth: '',
    activateAccount: '',
    deactivateAccount: '',
    hidedata: ''
  }
  changePassword(){
    let data={
      oldPassword: this.account.oldPassword,
      newPassword: this.account.newPassword,
      username:this.member_email_id
    }
    this.settingsService.changePassword(data)
    .subscribe(
      results => {
        // alert(JSON.stringify(results))
      //  alert(JSON.stringify(results))
      if(results.results=="1"){
        this.success_message="Password Updated...";
        this.error_message="";
      }
      if(results.results=="0"){
        this.success_message="";
        this.error_message="Invalid current password...";
      }
      
      },
      error => {
        console.log(error);
      });
  }
  

  showAccount(){
    this.router.navigate(['account/setting']);
  }
  showContact(){
    this.router.navigate(['account/email-sms']);
  }
  showHidedeleteprofile(){
    this.router.navigate(['account/HideDeleteProfile']);
  }

//  ******************************** new line************************
showHelp()
{
  this.router.navigate(['/account/help']);
}
showPrivacyOption()
{
  this.router.navigate(['/account/privacy']);
}
showPrivacyPolicy()
{
  this.router.navigate(['/account/privacy-policy']);
}
showTermConditions(){
  this.router.navigate(['/account/term-condition']);
  
}
}
