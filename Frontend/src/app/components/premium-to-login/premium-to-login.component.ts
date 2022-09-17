import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginModel } from 'src/app/models/login-model.model';
// import { SearchService } from '../../services/search.service';
// import { SharingService } from '../../services/sharing.service';
import { SearchService } from 'src/app/modules/member/services/search.service';
import { SharingService } from 'src/app/modules/updgrade/services/sharing.service';
import { LandingPageService } from 'src/app/services/landing-page.service';

@Component({
  selector: 'app-premium-to-login',
  templateUrl: './premium-to-login.component.html',
  styleUrls: ['./premium-to-login.component.css']
})
export class PremiumToLoginComponent implements OnInit {
  planDetails:any;
  isLoggedin: boolean = false; // for login button
  userID: string;
  setUserName: any;
  loginFailedMessage: string;
  showspinner=false;
  dots: any;
  moreText: any;
  btnText: any;
  member_id: number;
  memberDetails: any;
  preferenceDetails: any;
  login_id: any;
  gender:any;
  demoUrls: any;
  demoUrlsActive: any;
  item: any;
  len: any;
  display = "none"; //For Login Modal
  constructor(private landingPageServices: LandingPageService,
    private router:Router, 
    private searchService:SearchService,
    private sharingService: SharingService) { }

  ngOnInit(): void {
    this.getMemberPlansDetails();
  }
      //create field for login
      loginModel: LoginModel = {
        username: '',
        password: ''
      };
  callPayment( plan_name:any ,plan_amount:any){
    this.sharingService.sharingValue=plan_amount;
    // this.router.navigate(['members/payment/'+plan_name+'/'+plan_amount]);
    this.router.navigate(['upgrade/payment-pay/'+plan_name]);
  }
  getMemberPlansDetails() {
    this.searchService.getMemberPlansDetails()
      .subscribe(
        results => {
      // alert(JSON.stringify(results))
          this.planDetails = results.data;
        },
        error => {
          console.log(error);
        });
  }
   // ++++++++++++++++++++++++++++++++++++login +++++++++++++++++++++++++++++++++++++++++++++++

   login(): void {
    const data = {
      username: this.loginModel.username,
      password: this.loginModel.password
    };

    if(this.loginModel.username=='' || this.loginModel.password=='' )
    {
      this.loginFailedMessage = "fields should not blank";
    }else
    {
      this.showspinner=true;
      // alert(data.username+"-"+data.password);
      this.landingPageServices.doLogin(data)
        .subscribe(
          response => {
            // alert(JSON.stringify(response))
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
                    this.loginFailedMessage = "username and password not match, please try again";
                  });
                  // KAR HAI 
            }else
            {
              this.loginFailedMessage = "username and password not match, please try again";
            }
            this.showspinner=false;
            this.onCloseHandled();
          },
          error => {
            console.log(error);
            this.loginFailedMessage = "username and password not match, please try again";
          });
    }

  }
  onCloseHandled() {
    this.display = "none";
  }
  openModal() {
    this.display = "block";
    // return  this.display = "block";
  }
}
