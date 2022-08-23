import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Accounts } from '../../model/accounts.model';
import { SettingService } from '../../services/setting.service';

@Component({
  selector: 'app-hide-delete-profile',
  templateUrl: './hide-delete-profile.component.html',
  styleUrls: ['./hide-delete-profile.component.css']
})
export class HideDeleteProfileComponent implements OnInit {
  success_message:any;
  error_message:any;
  constructor(private router:Router,private settingsService:SettingService) { }

  member_id:any;
  member_email_id:any;
  ngOnInit(): void {
    // this.account.hidedata='3';

    $(document).ready(function(){
      $('button').click(function(){

        // alert(JSON.stringify($(this)))

        $('button').removeClass("imageb");
        $(this).addClass("imageb");
        
    });
    });
  
    this.member_id=localStorage.getItem('login_credentials');
    this.member_email_id=localStorage.getItem('login_credentials_email');
    this.member_id = localStorage.getItem('login_credentials');


    
    this.settingsService.ActivateData(this.member_id)
    .subscribe(
       data => {
         this.account.activateAccount=data.results;
       },
       err => console.log(err)
     );
     this.settingsService.saveprofiledata(this.member_id)
     .subscribe(
        data => {
        
          this.account.hidedata=data.results;
        },
        err => console.log(err)
      );
      
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
  
  account:Accounts={
    hide: '',
    unHide: '',
    username: '',
    oldPassword: '',
    newPassword: '',
    oneWeek: '',
    twoWeek: '',
    oneMonth: '',
    twoMonth: '',
    activateAccount: '',
    deactivateAccount: '',
    hidedata: ''
  }
  saveProfile(member_id:any)
  {
    let data={
      hide: this.account.hide,
      hide_period_time: this.account.hidedata,
    }
   
    // alert(JSON.stringify(data))
    this.settingsService.saveProfile(data)
    .subscribe(
      results => {
        // alert(JSON.stringify(results))
        if(results.Data.results=="1"){
          this.success_message="Hide profile...";
          this.error_message="";
        }
        if(results.Data.results=="0"){
          this.success_message="";
          this.error_message="UnHide profile...";
        }
      },
      error => {
        console.log(error);
      });
  }

  Activate(member_id:any){
    let data={
      activate_id: this.account.activateAccount,
      member_id:this.member_id,
}
    this.settingsService.Activate(data)
    .subscribe(
      results => {
        if(results.Data.results=="1"){
          this.success_message="  Activate Account...";
          this.error_message="";
        }
        if(results.Data.results=="0"){
          this.success_message="";
          this.error_message="Deactivate Account...";
        }
   },
      error => {
        console.log(error);
      });

  }


  hideData(value:any){
    this.account.hidedata=value;
  }
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
