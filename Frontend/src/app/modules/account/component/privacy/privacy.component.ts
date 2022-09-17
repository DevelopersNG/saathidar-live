import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { privacy } from '../../model/privacy.model';
import { SettingService } from '../../services/setting.service';

@Component({
  selector: 'app-privacy',
  templateUrl: './privacy.component.html',
  styleUrls: ['./privacy.component.css']
})
export class PrivacyComponent implements OnInit {

  constructor(private router:Router,private settingService :SettingService) { }

  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');

    if(this.member_id!=null && this.member_id!=''){
      this.member_id=localStorage.getItem('login_credentials');
      this.member_email_id=localStorage.getItem('login_credentials_email');
    }else{
      this.router.navigate(['']);
    }
    this.getPrivacyProfileDetails(this.member_id);
    this.getMessageClear();
  }
  getMessageClear(){
    this.phoneSuccess="";
    this.phoneError="";
  
    this.emailSuccess="";
    this.emailError="";
  
    this.photoSuccess="";
    this.photoError="";
  
    this.dobSuccess="";
    this.dobError="";
  
    this.annual_incomeSuccess="";
    this.annual_incomeError="";
  
    this.horoscopeSuccess="";
    this.horoscopeError="";
  
    this.visitors_settingSuccess="";
    this.visitors_settingError="";
  
    this.shortlist_settingSuccess="";
    this.shortlist_settingError="";
  
    this.profile_privacySuccess="";
    this.profile_privacyError="";
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
  
    member_id:any;
    member_email_id:any;
  
    phoneSuccess:any;
    phoneError:any;
  
    emailSuccess:any;
    emailError:any;
  
    photoSuccess:any;
    photoError:any;
  
    dobSuccess:any;
    dobError:any;
  
    annual_incomeSuccess:any;
    annual_incomeError:any;
  
    horoscopeSuccess:any;
    horoscopeError:any;
  
    visitors_settingSuccess:any;
    visitors_settingError:any;
  
    shortlist_settingSuccess:any;
    shortlist_settingError:any;
  
    profile_privacySuccess:any;
    profile_privacyError:any;
  
    privacyDetails:privacy={
      phone: '',
      email: '',
      photo: '',
      dob: '',
      annual_income: '',
      horoscope: '',
      visitors_setting: '',
      shortlist_setting: '',
      profile_privacy: '',
    }
  
  // get all privacy details
  getPrivacyProfileDetails(member_id:any){
    this.settingService.getAllPrivacyDetails(member_id)
      .subscribe(
        results => {
          // alert(JSON.stringify(results.data));
          this.privacyDetails.phone=results.data[0].phone;
          this.privacyDetails.email=results.data[0].email;
          this.privacyDetails.photo=results.data[0].photo;
          this.privacyDetails.dob=results.data[0].dob;
          this.privacyDetails.annual_income=results.data[0].annual_income;
          this.privacyDetails.horoscope=results.data[0].horoscope;
          this.privacyDetails.visitors_setting=results.data[0].visitors_setting;
          this.privacyDetails.shortlist_setting=results.data[0].shortlist_setting;
          this.privacyDetails.profile_privacy=results.data[0].profile_privacy;
          // alert(this.privacyDetails.shortlist_setting)
        },
        error => {
          console.log(error);
        });
  }
  
  
  // phone
    phoneDetails()
    {
      const data={
        member_id:this.member_id,
        valPhone:this.privacyDetails.phone
      }
      // alert(JSON.stringify(data));
  
      this.settingService.updatePhonePrivacy(data)
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          // alert(JSON.stringify(results.Data.results));
          if(results.Data.results=="1"){
            this.phoneSuccess="Phone Privacy Updated...";
            this.phoneError="";
          }
          if(results.Data.results=="0"){
            this.phoneSuccess="";
            this.phoneError="Something Wrong !!, Phone Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
  

    // photo
    photoDetails()
    {
      const data={
        member_id:this.member_id,
        valPhoto:this.privacyDetails.photo
      }
      // alert(JSON.stringify(data));
  
      this.settingService.updatephotoPrivacy(data)
      .subscribe(
        results => {

          // alert(JSON.stringify(results));
       
          if(results.Data.results=="1"){
            this.photoSuccess="photo Privacy Updated...";
            this.photoError="";
          }
          if(results.Data.results=="0"){
            this.photoSuccess="";
            this.photoError="Something Wrong !!, Phone Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
  
    // email
    EmailDetails(){
      const data={
        member_id:this.member_id,
        valEmail:this.privacyDetails.email
      }
      // alert(JSON.stringify(data));
  
      this.settingService.updateEmailPrivacy(data)
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          if(results.Data.results=="1"){
            this.emailSuccess="Email Privacy Updated...";
            this.emailError="";
          }
          if(results.Data.results=="0"){
            this.emailSuccess="";
            this.emailError="Something Wrong !!, Email Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
  
    }
  
    // DOB
    DOBDetails(){
      const data={
        member_id:this.member_id,
        valDob:this.privacyDetails.dob
      }
      // alert(JSON.stringify(data));
  
      this.settingService.updateDOBPrivacy(data)
      .subscribe(
        results => {
          if(results.Data.results=="1"){
            this.dobSuccess="Date Of Birth Privacy Updated...";
            this.dobError="";
          }
          if(results.Data.results=="0"){
            this.dobSuccess="";
            this.dobError="Something Wrong !!, Date Of Birth Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
  
    // Annual Income
    AnnualDetails(){
      const data={
        member_id:this.member_id,
        valAnnual_income:this.privacyDetails.annual_income
      }
  
      this.settingService.updateAnnualIncomePrivacy(data)
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          if(results.Data.results=="1"){
            this.annual_incomeSuccess="Annual Income Privacy Updated...";
            this.annual_incomeError="";
          }
          if(results.Data.results=="0"){
            this.annual_incomeSuccess="";
            this.annual_incomeError="Something Wrong !!, Annual Income Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
  
    // Horoscope
    HoroscopeDetails(){
      const data={
        member_id:this.member_id,
        valHoroscope:this.privacyDetails.horoscope
      }
  
      this.settingService.updateHoroscopePrivacy(data)
      .subscribe(
        results => {
          if(results.Data.results=="1"){
            this.horoscopeSuccess="Horoscope Privacy Updated...";
            this.horoscopeError="";
          }
          if(results.Data.results=="0"){
            this.horoscopeSuccess="";
            this.horoscopeError="Something Wrong !!, Horoscope Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
    // Account Visitors
    AccountVisitorsDetails(){
      const data={
        member_id:this.member_id,
        valVisitors_setting:this.privacyDetails.visitors_setting
      }
      this.settingService.updateAccountVisitorsPrivacy(data)
      .subscribe(
        results => {
          if(results.Data.results=="1"){
            this.visitors_settingSuccess="Account Visitors Privacy Updated...";
            this.visitors_settingError="";
          }
          if(results.Data.results=="0"){
            this.visitors_settingSuccess="";
            this.visitors_settingError="Something Wrong !!, Account Visitors Privacy Not Updated...";
          }
        },
        error => {
          console.log(error);
        });
    }
  
   // ShortList
   ShortListDetails(){
    const data={
      member_id:this.member_id,
      valShortlist_setting:this.privacyDetails.shortlist_setting
    }
    this.settingService.updateShortListPrivacy(data)
    .subscribe(
      results => {
        if(results.Data.results=="1"){
          this.shortlist_settingSuccess="ShortList Privacy Updated...";
          this.shortlist_settingError="";
        }
        if(results.Data.results=="0"){
          this.shortlist_settingSuccess="";
          this.shortlist_settingError="Something Wrong !!, ShortList Privacy Not Updated...";
        }
      },
      error => {
        console.log(error);
      });
  }
  
  // Profile Privacy
  ProfilePrivacyDetails(){
    const data={
      member_id:this.member_id,
      valProfile_privacy:this.privacyDetails.profile_privacy
    }
  
    this.settingService.updateProfilePrivacy(data)
    .subscribe(
      results => {
        if(results.Data.results=="1"){
          this.profile_privacySuccess="Profile Privacy Updated...";
          this.profile_privacyError="";
        }
        if(results.Data.results=="0"){
          this.profile_privacySuccess="";
          this.profile_privacyError="Something Wrong !!, Profile Privacy Not Updated...";
        }
      },
      error => {
        console.log(error);
      });
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
