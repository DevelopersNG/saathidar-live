import { Component, OnInit } from '@angular/core';
import { ShortRegistrationBasicDetails, ShortRegistrationModel } from 'src/app/models/short-registration-model.model';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-member-basic-details',
  templateUrl: './member-basic-details.component.html',
  styleUrls: ['./member-basic-details.component.css']
})
export class MemberBasicDetailsComponent implements OnInit {
  member_id:any;
  user_id:any;
  phone:any;
  email:any;
  constructor(private landingPageServices: LandingPageService,
    private router: Router,) { }
  ngOnInit(): void {
    
    this.member_id=localStorage.getItem('login_credentials');
    localStorage.setItem('login_credentials', "");
    localStorage.setItem('login_credentials_email', "");
    localStorage.setItem('setUserName', "");
    this.user_id=localStorage.getItem('user_id');
    this.phone= localStorage.getItem('login_credentials_phone');
    this.email= localStorage.setItem('login_credentials_email', "");
    this.callCountryName();
    this.getReligionName();
  }

  checkCValidationForButton: any;
  ClassError: any;
  casteName:any;
  religionName:any;
  countryName:any;
  isGothraAndAstroShow: boolean = false;
  isShowCastAndSubCast: boolean = true;
  memberBasicForm:ShortRegistrationBasicDetails = {
    Country: '',
    marital_status: '',
    Date_of_birth: '',
    Height: '',
    Religion: '',
    Cast: '',
    Diet: ''
  }
  CountrynameError = '';
  maritalStatusnameError ='';
  DateofbirthnameError = '';
  HeightnameError = '';
  ReligionnameError ='';
  DietnameError ='';

  BasicForm(user_id:any){
    // this.otpDisplay = "block";
    this.checkCValidationForButton=true;
    this.CountrynameError='';
    if (this.memberBasicForm.Country == '') {
      this.CountrynameError = 'Country name should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.memberBasicForm.marital_status == '') {
      this.maritalStatusnameError = 'marital Status should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.memberBasicForm.Date_of_birth == '') {
      this.DateofbirthnameError = 'Date of birth should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.memberBasicForm.Height == '') {
      this.HeightnameError = 'Height should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.memberBasicForm.Religion == '') {
      this.ReligionnameError = 'Religion should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.memberBasicForm.Diet == '') {
      this.DietnameError = 'Diet should not be blank';
      this.checkCValidationForButton = false;
    }
    const data={
      country_name:this.memberBasicForm.Country,
      marital_status:this.memberBasicForm.marital_status,
      date_of_birth:this.memberBasicForm.Date_of_birth,
      height:this.memberBasicForm.Height,
      religion:this.memberBasicForm.Religion,
      Cast:this.memberBasicForm.Cast,
      lifestyles:this.memberBasicForm.Diet,
    }
    if(this.checkCValidationForButton){
      this.landingPageServices.BasicFormDetails(user_id,data)
      .subscribe(
       results => {
        this.memberRegistrationMessage = results.message;
   if(results.results == '1')
   {
    this.otpDisplay = "block";
    // this.router.navigate(['matches/new']);
   }
        },
        error => {
          console.log(error);
      });
    }
  }
  getReligionName() {
    this.landingPageServices.getReligionNameBasic()
      .subscribe(
        results => {
          this.religionName = results.religion;
        },
        error => {
          console.log(error);
        });
  }
  callCountryName() {
    this.landingPageServices.getCountryName()
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          this.countryName = results.country;
          // alert(this.countryName);
        },
        error => {
          console.log(error);
        });
  }

  gothraShowAndHide(event: any) {
    const religion_Name = event.target.value;
    this.landingPageServices.getCastNameBYReligionName(religion_Name)
      .subscribe(
        results => {
          this.casteName = results.cast;
          // alert(this.stateName);
        },
        error => {
          console.log(error);
        });

    // 1- Hindu, 6-Jain
    if (event.target.value == 'Hindu' || event.target.value == 'Jain') {
      this.isGothraAndAstroShow = true;
    } else {
      this.isGothraAndAstroShow = false;
    }
    // 5- Parsi , 7- Buddhist, 8-Jewish, 9-No Religion, 10-Spiritual - not religious, 11-Other
    if (event.target.value == 'Parsi' || event.target.value == 'Buddhist' || event.target.value == 'Jewish' || event.target.value == 'No Religion' || event.target.value == 'Spiritual' || event.target.value == 'Other') {
      this.isShowCastAndSubCast = false;
    } else {
      this.isShowCastAndSubCast = true;
    }
  }
  // ++++++++++++++++++++++++++++++++++++++++++++++landing page ++++++++++++++++++++++++++++
  otpDisplay = "none";
  otpErrorMessage: string;
  otpMessage:string;
  memberRegistrationMessage: string;
  shortRegistrationModel: ShortRegistrationModel = {
    firstName: '',
    lastName: '',
    gender: '',
    phone: '',
    profilecreatedby: 'Self',
    email: '',
    otp: '',
    role: '',
    forgot_pwd_phone: '',
    forgot_pwd_otp: '',
    franchise_code: '',
    otp_main_registration: '',
    mobile_number_registration: '',
    franchiseCode: ''
  };
    // close modal for otp
    onCloseHandledForOTP() {
      this.otpDisplay = "none";
    }
     // onchange event on OTP
    // verify otp and short registrationsS
    OtpVerifyAndShortRegisteration() {
      this.router.navigate(['']);
    }
    hideSubmit=false
      // onchange event on OTP
  public onChangeOTP(event: Event): void {
    let data = {
      phone: this.phone,
      otp:this.shortRegistrationModel.otp
    };
    this.landingPageServices.verifyOTP(data.otp,data.phone)
      .subscribe(
        results => {
          if (results.message == null) {    // if otp verify check
            this.otpErrorMessage = "otp not match..";
          }
          this.otpMessage = results.message;
          this.hideSubmit=true;
          
        },
        error => {
          console.log(error);
          this.otpErrorMessage = "otp not match..please try again";
        });
  }
}
