import { Component, OnInit } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SharingService } from 'src/app/modules/search/services/sharing.service';
// import { SearchService } from 'src/app/modules/updgrade/services/search.service';
import { SearchService } from 'src/app/modules/member/services/search.service';
import * as $ from 'jquery';
import { LoginModel } from 'src/app/models/login-model.model';
import { LandingPageService } from 'src/app/services/landing-page.service';
@Component({
  selector: 'app-member-profile',
  templateUrl: './member-profile.component.html',
  styleUrls: ['./member-profile.component.css']
})
export class MemberProfileComponent implements OnInit {
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
  imageURL='http://103.150.186.33:8080'
  loader = false;
  memberIDs: any;
  display = "none"; //For Login Modal
  genderImageURL='/saathidaar/assets/img'
  private routeSub: Subscription;
  constructor(private landingPageServices: LandingPageService,
    private route: ActivatedRoute,
    private searchServices: SearchService,
    private sharingService: SharingService,
    private router: Router) { }

    ngOnInit(): void {
      $(document).ready(function () {
        $(function () {
          $('.hidden-menu').delay(2000).slideToggle("slow");
        });
      });
      $(document).ready(function () {
        $('img').click(function () {
          // alert(JSON.stringify($(this)))
          $('img').removeClass("imageb");
          $(this).addClass("imageb");
        });
      });
      this.routeSub = this.route.params.subscribe(params => {
        this.member_id = params['member_id'];
        // value are getting for next and previous start
        var number = sessionStorage.getItem("value" || "[]");
        if (number == null) {
          this.item = [];
        } else {
          this.item = JSON.parse(number);
        }
        // alert(this.item)
        if (this.item == null) {
          // this.item= this.sharingService.sharingValue;
          this.item = sessionStorage.getItem("value");
        }
        for (var k = 0; k < this.item.length; k++) {
          if (this.item[k] == this.member_id) {
            this.i = k;
          }
        }
        // value are getting for next and previous end
        this.login_id = localStorage.getItem('login_credentials');
        // alert(this.login_id)
        this.getOtherImage(this.member_id);
        this.setMemberDetailsByMemberID(this.member_id);
        this.getMyImage(this.login_id);
        this.setPartnerPreference(this.member_id, this.login_id);
    // start
    this.gender = localStorage.getItem('login_credentials_gender');
    if(this.gender=='male'){
      this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
    }
    if(this.gender=='female'){
      this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
    }
    // end
      });
    }

      //create field for login
  loginModel: LoginModel = {
    username: '',
    password: ''
  };
    i: any;
    previous() {
      var len = this.item.length;
      var current = this.item[this.i];
      var previous = this.item[(this.i + len - 1) % len];
      this.member_id = previous;
      this.router.navigate(['members/profile/' + previous]);
    }
    Next() {
      var len = this.item.length;
      var current = this.item[this.i];
      // alert(this.item)
      var next = this.item[(this.i + 1) % len];
      this.member_id = next;
      this.router.navigate(['members/profile/' + next]);
    }
    openModal() {
      this.display = "block";
      // return  this.display = "block";
    }
    setMemberDetailsByMemberID(member_id: number) {
      this.searchServices.getMemberDetailsByMemberID(member_id, this.login_id)
        .subscribe(
          results => {
            // alert((results.data.my_profile_photo))
            this.memberDetails = results.data;
            // alert(JSON.stringify(this.memberDetails.email_id))
            console.log(this.memberDetails);
            
            // alert(this.memberDetails.gender)
          },
          error => {
            console.log(error);
          });
    }
    setPartnerPreference(member_id: number, login_id: number) {
      // alert(member_id+login_id)
      this.searchServices.getPartnerPreference(member_id, login_id)
        .subscribe(
          results => {
            // alert(JSON.stringify(results));
  
            // alert(this.preferenceDetails )
            this.preferenceDetails = results;
            // alert(JSON.stringify(results))
            // this.preferenceDetails.my_annual_income="NO";
          },
          error => {
            console.log(error);
          });
    }
    ngOnDestroy() {
      this.routeSub.unsubscribe();
    }
    // ****************************************** STart
    profileIMags:any;
    callIMageForMemberID(member_id:any){
      this.searchServices.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        // alert(JSON.stringify(results))
        this.profileIMags=results.data[1].member_images;
        this.demoUrls=results.data;
          // this.demoUrls.push(results);
        // alert(this.demoUrls)
       });
    }
    // *********************************************** End
  
    seturl:any;
    loadTIme:boolean=false;
    change(){
      this.loadTIme=false;
    }
    showimage(url:any)
    {
     this.seturl=url;
     this.loadTIme=true;
    }
    
    check(val:any){
    // alert(val)
    
      }
    // ********************************prpfile details*********************
    profileIMagsOther:any;
  
    getOtherImage(member_id: any) {
      this.searchServices.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        // alert(JSON.stringify(results))
        // alert(JSON.stringify(results))
        this.profileIMagsOther=results.data[0].member_images;
        // this.demoUrls=results.data;
       });
    }
    getMyImage(member_id: any) {
      this.searchServices.getDataImageDataByURL(member_id)
      .subscribe((results : any) => {
        // alert(JSON.stringify(results))
        this.profileIMags=results.data[0].member_images;
        // this.demoUrls=results.data;
       });
    }
    sentRequests(member_to_id: string) {
      const data = {
        request_from_id: this.member_id,
        request_to_id: member_to_id,
        request_status: "Pending"
      }
      this.searchServices.sentRequests(data)
        .subscribe(
          results => {
      // alert(JSON.stringify(results))
            // if requests are send successfully then call member details again
            if (results != null) {
              this.callSearchDetails(this.member_id);
            }
          },
          error => {
            this.loader = false;
            console.log(error);
            window.location.reload();
  
          });
  
      // alert(JSON.stringify(data));
    }
    searchMemberDetails:any;
    callSearchDetails(member_id: any) {
      this.loader = true;
      this.searchServices.searchNewMatchesAllMember(member_id)
        .subscribe(
          results => {
            // alert(JSON.stringify())
            this.searchMemberDetails = results.data;
            // this is for next and previous- start
            this.memberIDs = [];
            for (var i = 0; i < this.searchMemberDetails.length; i++) {
              this.memberIDs.push(this.searchMemberDetails[i]['member_id']);
            }
            // alert(this.memberIDs);
            // this.sharingService.sharingValue=this.memberIDs;  
            sessionStorage.setItem("value", JSON.stringify(this.memberIDs));
            // this is for next and previous- end  
            this.loader = false;
          },
          error => {
            this.loader = false;
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
  }

  function x(x: any, arg1: (any: any) => boolean) {
    throw new Error('Function not implemented.');
  }
  