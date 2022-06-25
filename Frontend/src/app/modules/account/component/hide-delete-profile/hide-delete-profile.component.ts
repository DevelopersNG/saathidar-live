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

  constructor(private router:Router,private settingsService:SettingService) { }

  member_id:any;
  member_email_id:any;
  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
    this.member_email_id=localStorage.getItem('login_credentials_email');
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
    aeactivateAccount: '',
    hidedata: ''
  }
  saveProfile()
  {
    let data={
      hide: this.account.hide,
      hide_period_time: this.account.hidedata,
    }

    alert(JSON.stringify(data))


    // this.settingsService.saveProfile(this.member_id,data)
    // .subscribe(
    //   results => {
    //    alert(results.message)

    //   },
    //   error => {
    //     console.log(error);
    //   });
  }
}
