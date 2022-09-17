import { Component, ElementRef, OnInit } from '@angular/core';
import { LoginModel } from 'src/app/models/login-model.model';
import { ShortRegistrationModel } from 'src/app/models/short-registration-model.model';
import { MemberPlanModels } from 'src/app/models/member-plan-models.model';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, UrlSegment } from "@angular/router";
import { Location } from '@angular/common';
import { SearchModel } from 'src/app/models/search-model.model';
import { SearchComponent } from 'src/app/modules/member/components/search/search.component';
import * as jQuery from 'jquery';
import { ConstantService } from 'src/constant/constant.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  // myForm: FormGroup;
  // assetsImage='/assets/css/css4.css'
  forgetPasswordM=false;
  data:any;
  icon:any;
  assets:any;
  loader=false;
  Franchies=false;
  notifaction= 0;
  showspinner=false;
  member_id: any;
  title = 'Search';
  _router: string;
  _route: any;
  display = "none"; //For Login Modal
  otpDisplay = "none";
  otpErrorDisplay = "none";
  isLoggedin: boolean = false; // for login button
  setUserName: any;
  loginFailedMessage: string;
  userID: string;
  textShow: any;
  otpMessage: string;
  otpErrorMessage: string;
  memberRegistrationMessage: string;
  checkCValidationForButton: any;
  registerUserMessage: string;
  religionName:any;
  imageURL='http://103.174.102.195:8080'
  // fetch plan details 
  planDetails: any;
  // fetch serach details on home
  searchMemberDetails: any;
  firstnameError = '';
  lastnameError = '';
  emailError = '';
  phoneError = "";
  FranchiseError="";
  SendoptMasseage=false
  //create field for login
  loginModel: LoginModel = {
    username: '',
    password: ''
  };
  //model for short registrations
  shortRegistrationModel: ShortRegistrationModel = {
    firstName: '',
    lastName: '',
    gender: '',
    phone: '',
    profilecreatedby: '',
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
  // model for member plans
  memberPlans: MemberPlanModels = {
    plan_id: '',
    plan_name: '',
    plan_validity: '',
    plan_price: ''
  };
  // model for search on homepage
  searchModel: SearchModel = {
    lookingFor: '',
    from_age: '',
    to_age: '',
    religion_name: '',
    marital_status: ''
  };
  constructor(private landingPageServices: LandingPageService,
    private constant:ConstantService,
    private router: Router,
    private elementRef: ElementRef,
    private activatedRoute: ActivatedRoute,
    private location: Location,
    private searchMember: SearchComponent) {
    this._router = this.location.path();
  } // bind with service class 
    //  loadData(){
    //    this.showspinner=true;
    //    setTimeout(()=>{
    //      this.showspinner= false;
    //    },5000);
    //  }4
  ngOnInit() {
    // ***************************Franchies
    $('.box1').on('click', function() {
      $('.box5').fadeToggle("slow");
        });
    if (localStorage) {
      if (localStorage.getItem('login_credentials') != null && localStorage.getItem('login_credentials') != "") {
        this.setUserName = localStorage.getItem('setUserName');
        this.isLoggedin = true;
      }
      this.hideDiv();
    }
    this.callReligionList();
    this.getMemberPlansDetails();
    this.checkCValidationForButton = true;
    this.icon=this.constant.icon;
    // this.icon=this.constant.icon;
    this.assets=this.constant.assets;
    this.IMageForMemberID(this.data)
  }
  callReligionList() {
    this.landingPageServices.getReligionName()
    .subscribe(
      results => {
        this.religionName = results;
      },
      error => {
        console.log(error);
      });
  }
  // show div for otp
  showDiv() {
    this.textShow = true;
  }
  hideDiv() {
    this.textShow = false;
  }
  logout(): void {
    localStorage.setItem('login_credentials', "");
    localStorage.setItem('login_credentials_email', "");
    localStorage.setItem('setUserName', "");
    localStorage.setItem('user_id',"");
    localStorage.setItem('login_credentials_phone',"");
    this.isLoggedin = false;
  }
  getMemberPlansDetails() {
    this.landingPageServices.getMemberPlansDetails()
      .subscribe(
        results => {
          this.planDetails = results.data;
        },
        error => {
          console.log(error);
        });
  }

  login(): void {
    const data = {
      username: this.loginModel.username,
      password: this.loginModel.password
    };
    if(this.loginModel.username=='' || this.loginModel.password=='' )
    {
      this.loginFailedMessage = "fields should not blank";
    }
    else
    {
      this.showspinner=true;
      this.landingPageServices.doLogin(data)
        .subscribe(
          response => {
            console.log(response);
            // this.submitted = true;
            if (response.results == '1' || response.results == '1') {
              this.isLoggedin = true;
              this.setUserName = response.username;
              this.userID = response.id;
              localStorage.setItem('key', 'value');
              this.landingPageServices.getMemberIDByUserLogin(this.userID)
                .subscribe(
                  results => {
                    localStorage.setItem('login_credentials', results.member_id);
                    localStorage.setItem('login_credentials_profile_id', results.profile_id);
                    localStorage.setItem('setUserName', this.setUserName);
                    localStorage.setItem('login_credentials_email', response.email);
                    localStorage.setItem('login_credentials_phone', response.phone);
                    localStorage.setItem('login_credentials_firstname', response.firstName);
                    localStorage.setItem('login_credentials_lastname', response.lastName);
                    localStorage.setItem('login_credentials_gender', response.gender);
                    // To retrieve the data & save it to an existing variable
                    if (localStorage) {
                      let data1 = localStorage.getItem('login_credentials');
                    }
                    this.router.navigate(['matches/new']);
                  },
                  error => {
                    console.log(error);
                    this.loginFailedMessage = "username & password does not matched, please try again";
                  });
                  // KAR HAI 
            }else
            {
              this.loginFailedMessage = "username & password does not matched, please try again";
            }
            this.showspinner=false;
            this.onCloseHandled();
          },
          error => {
            console.log(error);
            this.loginFailedMessage = "username & password does not matched, please try again";
          });
    }
  }
  // open modal for login
  openModal() {
    this.display = "block";
    // return  this.display = "block";
  }
  // close modal for login
  onCloseHandled() {
    this.display = "none";
  }
  openotp()
  {
    this.display = "none";
  }
  // verify otp and short registrations
  OtpVerifyAndShortRegisteration() {
    const registrationsData = {
      firstName: this.shortRegistrationModel.firstName,
      lastName: this.shortRegistrationModel.lastName,
      gender: this.shortRegistrationModel.gender,
      profilecreatedby: this.shortRegistrationModel.profilecreatedby,
      email: this.shortRegistrationModel.email,
      phone: this.shortRegistrationModel.phone,
      franchise_code:this.shortRegistrationModel.franchise_code,
      role: "USER"
    };
    
    this.landingPageServices.shortMemberRegistrations(registrationsData)
      .subscribe(
        results => {
          this.memberRegistrationMessage = results.message;
          this.otpDisplay = "none";
          this.display = "block";
          // this.callShortRegistrationBlank();
          // if(results== '1')
          // {
          //   this.router.navigate(['/member_details_login/'+this.member_id]);
          // }
        },
        error => {
          console.log(error);
          this.memberRegistrationMessage = "member not register....";
        });
  }
  // clear the fields
  // callShortRegistrationBlank() {
  //   this.shortRegistrationModel.firstName = "";
  //   this.shortRegistrationModel.lastName = "";
  //   this.shortRegistrationModel.gender = "";
  //   this.shortRegistrationModel.profilecreatedby = "";
  //   this.shortRegistrationModel.email = "";
  //   this.shortRegistrationModel.phone = "";
  //   this.shortRegistrationModel.role = "";
  // }
  // onchange event on OTP
  public onChangeOTP(event: Event): void {
    let data = {
      phone: this.shortRegistrationModel.phone,
      otp: this.shortRegistrationModel.otp
    };
    this.landingPageServices.verifyOTP(data.otp, data.phone)
      .subscribe(
        results => {
          if (results.message == null) {    // if otp verify check
            this.otpErrorMessage = "otp not match..";
          }
          this.otpMessage = results.message;
        },
        error => {
          console.log(error);
          this.otpErrorMessage = "otp not match..please try again";
        });
  }

  // open modal for otp and send otp
  openModalForOTP() {
    // var fran_code=(<HTMLInputElement>document.getElementById("FranchiesCode")).value;
    this.loader=true;
    this.otpErrorMessage = "";
    this.otpMessage = "";
    this.memberRegistrationMessage = "";
    this.checkCValidationForButton = true;

    if (this.shortRegistrationModel.firstName == '') {
      this.firstnameError = 'First name should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.shortRegistrationModel.lastName == '') {
      this.lastnameError = 'Last name should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.shortRegistrationModel.email == '') {
      this.emailError = 'Email should not be blank';
      this.checkCValidationForButton = false;
    }
    if (this.shortRegistrationModel.phone == '') {
      this.phoneError = 'Mobile number should not be blank';
      this.checkCValidationForButton = false;
    }
    // if(this.shortRegistrationModel.profilecreatedby=="Franchise")
    // {
    //   if (fran_code =='') {
    //     this.FranchiseError = 'Franchise code should not be blank';
    //     this.checkCValidationForButton = false;
    //   }
    // }
    // this.router.navigate(['/member_details_login/'+this.member_id]);
    if (this.checkCValidationForButton) {
      const data = {
        // phone: this.shortRegistrationModel.phone,
        firstName: this.shortRegistrationModel.firstName,
        lastName: this.shortRegistrationModel.lastName,
        gender: this.shortRegistrationModel.gender,
        profilecreatedby: this.shortRegistrationModel.profilecreatedby,
        email: this.shortRegistrationModel.email,
        phone: this.shortRegistrationModel.phone,
        // franchise_code:fran_code,
        role: "USER"
      };

      this.landingPageServices.shortMemberRegistrations(data)
        .subscribe(
          results => {
            if (results.results == '1') {
              localStorage.setItem('user_id',results.user_id);
              localStorage.setItem('login_credentials_phone', this.shortRegistrationModel.phone);
              localStorage.setItem('login_credentials', results.member_id);
              localStorage.setItem('login_credentials_profile_id', results.profile_id);
              localStorage.setItem('setUserName', this.setUserName);
              localStorage.setItem('login_credentials_email',this.shortRegistrationModel.email);
              localStorage.setItem('login_credentials_phone', this.shortRegistrationModel.phone);
              localStorage.setItem('login_credentials_firstname', this.shortRegistrationModel.firstName);
              localStorage.setItem('login_credentials_lastname', this.shortRegistrationModel.lastName);
              localStorage.setItem('login_credentials_gender', this.shortRegistrationModel.gender);
              this.router.navigate(['/member_details_login/'+results.user_id,]);
            } 
            // else if (results.message == 'error') {
            //   this.registerUserMessage = "otp not send please try again...";
            //   this.otpDisplay = "none";
            //   this.otpErrorDisplay = "block";
            // } else if (results.message != 'success') {
            //   this.registerUserMessage = results.message;
            //   this.otpDisplay = "none";
            //   this.otpErrorDisplay = "block";
            // }
            // this.loader=false;
          },
          error => {
            console.log(error);
          });
      // this.otpDisplay = "block";
    }
  }
  // close modal for otp
  onCloseHandledForOTP() {
    this.otpDisplay = "none";
  }
  // close modal for otp
  onCloseHandledForErrorOTP() {
    this.otpErrorDisplay = "none";
  }
  // validation start
  onKeyUpEventForFirstName(event: any) {
    var charCode = event.keyCode;
    if ((charCode > 65 && charCode < 90) || (charCode > 97 && charCode < 122)) {
      this.firstnameError = '';
      this.checkCValidationForButton = true;
    } else {
      this.checkCValidationForButton = false;
      this.firstnameError = 'Please provide only alphabet';
    }
  }
  onKeyUpEventForLastName(event: any) {
    var charCode = event.keyCode;
    if ((charCode > 65 && charCode < 90) || (charCode > 97 && charCode < 122)) {
      this.lastnameError = '';
      this.checkCValidationForButton = true;
    } else {
      this.checkCValidationForButton = false;
      this.lastnameError = 'Please provide only alphabet';
    }
  }

  isValidMailFormat(event: any) {
    let cont = event.target.value;
    let EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    if (cont != "" && (cont.length <= 7 || !EMAIL_REGEXP.test(cont))) {
      this.emailError = "Please provide a valid email";
      // return { "Please provide a valid email": true };
      this.checkCValidationForButton = false;
    } else {
      this.checkCValidationForButton = true;
      this.emailError = "";
    }
  }

  isValidMobileNumber(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    let cont = event.target.value;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar) && cont.length <= 10) {
      this.phoneError = "";
      this.checkCValidationForButton = true;
    } else {
      this.phoneError = "Please provide a valid mobile number";
      this.checkCValidationForButton = false;
    }
  }
  
  // validation end 
  goToSearchPage() {

    if (localStorage) {
      if (localStorage.getItem('login_credentials') != null && localStorage.getItem('login_credentials') != "") {
        this.setUserName = localStorage.getItem('setUserName');
        this.member_id = localStorage.getItem('login_credentials');
        this.isLoggedin = true;
        const data = {
          lookingFor: this.searchModel.lookingFor,
          from_age: this.searchModel.from_age,
          to_age: this.searchModel.to_age,
          religion_name: this.searchModel.religion_name,
          marital_status: this.searchModel.marital_status
        };
  
        localStorage.setItem("homepage_search_details", JSON.stringify(data));
        this.router.navigate(['/matches/members']);
      } else {
      }
    } else {
    }
  }
  OpenSubcribe()
  {
    if (localStorage) {
      if (localStorage.getItem('login_credentials') != null && localStorage.getItem('login_credentials') != "") {
        this.router.navigate(['upgrade/plan']);
      }else{
        this.router.navigate(['']);
      }
    }
  }

// *************forgot password*******************
// onchange event on OTP

public onChangeSendOTP(event: Event): void {
  let data = {
    email: this.shortRegistrationModel.email,
  };
  this.landingPageServices.sendOTPForgotpassword(data)
    .subscribe(
      results => {
       this.SendoptMasseage=true
        if (results.message == null) {    // if otp verify check   
          this.otpErrorMessage = "otp not match..";
        }
        this.otpMessage = results.message;
      },
      error => {
        console.log(error);
        this.otpErrorMessage = "otp not match..please try again";
      });
}

public submitForgotPassword(){
  let data = {
    email: this.shortRegistrationModel.email,
    otp: this.shortRegistrationModel.forgot_pwd_otp,
  };
  this.landingPageServices.saveForgotPassword(this.shortRegistrationModel.forgot_pwd_otp,this.shortRegistrationModel.email)
    .subscribe(
      results => {
      
        if (results.results == '1') {    // if otp verify check  
          let data = {
            email: this.shortRegistrationModel.email,
          };
          this.landingPageServices.sendEmail(data)
            .subscribe(

              results => {
                this.forgetPasswordM=true;
        
                this.otpMessage = results.message;
              },
              error => {
                console.log(error);
                this.otpErrorMessage = "otp not match..please try again";
              });
          // this.otpErrorMessage = "otp not match..";
        }
        this.otpMessage = results.message;
      },
      error => {
        console.log(error);
        this.otpErrorMessage = "otp not match..please try again";
      });
}
demoUrls:any
IMageForMemberID(data:any){
  this.landingPageServices.getImageSuccessStory()
  .subscribe((results : any) => { 
    this.demoUrls=results.data;
   });
}
  
FranchiesOption()
{
  if(this.shortRegistrationModel.profilecreatedby=='Franchies')
{
    this.Franchies=true
}else{
    this.Franchies=false
}
}

}
  