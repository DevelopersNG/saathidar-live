import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { SettingService } from '../../services/setting.service';
import { Email } from '../../model/privacy.model';
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  constructor(private router:Router,
    private settingService :SettingService) { }
  ngOnInit(): void {
     this.member_id=localStorage.getItem('login_credentials');

    if(this.member_id!=null && this.member_id!=''){
      this.member_id=localStorage.getItem('login_credentials');
      this.member_email_id=localStorage.getItem('login_credentials_email');
    }else{
      this.router.navigate(['']);
    }
    this.getSMSEMailSettingsDetails(this.member_id);
    this.success_message="";
    this.error_message="";
  }
  member_id:any;
  member_email_id:any;
  success_message:any;
  error_message:any;
  email:Email={
    Premium_Match_Mail: '',
    RecentVisitorsEmail: '',
    ContactAlert: '',
    SMSAlert: '',
    MessageReceivedAlert: ''
  }
  getSMSEMailSettingsDetails(member_id:any){
    this.settingService.getAllEmailSettingDetails(member_id)
    .subscribe(
      results => {
        // alert(JSON.stringify(results));
        this.email.Premium_Match_Mail=results.data[0].premium_match_mail;
        this.email.RecentVisitorsEmail=results.data[0].recent_visitors_email;
        this.email.ContactAlert=results.data[0].contact_alert;
        this.email.SMSAlert=results.data[0].sms_alert;
        this.email.MessageReceivedAlert=results.data[0].message_received_alert;
      },
      error => {
        console.log(error);
      });
  }
  updateEmailSettings(){
    const data={
      premium_match_mail: this.email.Premium_Match_Mail,
      recent_visitors_email: this.email.RecentVisitorsEmail,
      contact_alert: this.email.ContactAlert,
      sms_alert: this.email.SMSAlert,
      message_received_alert: this.email.MessageReceivedAlert,
      member_id:this.member_id
    }
    this.settingService.updateAllEmailSettingsDetails(data)
    .subscribe(
      results => {
        // alert(JSON.stringify(results.Data.results));
        if(results.Data.results=="1"){
          this.success_message="Emails/SMS Alert Updated...";
          this.error_message="";
        }
        if(results.Data.results=="0"){
          this.success_message="";
          this.error_message="Emails/SMS Alert Updated...";
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
