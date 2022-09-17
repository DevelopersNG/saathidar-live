import { Component, OnInit } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SharingService } from 'src/app/modules/search/services/sharing.service';
import { SearchService } from '../../services/search.service';
import * as $ from 'jquery';
@Component({
  selector: 'app-profile-alldetails',
  templateUrl: './profile-alldetails.component.html',
  styleUrls: ['./profile-alldetails.component.css']
})
export class ProfileAlldetailsComponent implements OnInit {
  dots: any;
  moreText: any;
  btnText: any;
  member_id: number;
  memberDetails: any;
  preferenceDetails: any;
  login_id: any;
  imageURL='http://103.174.102.195:8080'
  loader = false;
  memberIDs: any;
  sendRequest=false
  
  private routeSub: Subscription;
  constructor(private route: ActivatedRoute,
    private searchServices: SearchService,
    private sharingService: SharingService,
    private router: Router) { }
    gender:any;
    demoUrls: any;
    demoUrlsActive: any;
    item: any;
    len: any;
    genderImageURL='/saathidaar/assets/img'
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
  setMemberDetailsByMemberID(member_id: number) {
    this.searchServices.getMemberDetailsByMemberID(member_id, this.login_id)
      .subscribe(
        results => {
          // alert((results))
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
  
  

   // ****************************************** STart

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
  sentRequests(member_to_id: string,my_premium_status:string) {
    if(my_premium_status == '2')
    {
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
          window.location.reload();
        },
        error => {
          this.loader = false;
          console.log(error);
          window.location.reload();
        });
      }
        else
    {
      this.sendRequest=true;
    }
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




}
function x(x: any, arg1: (any: any) => boolean) {
  throw new Error('Function not implemented.');
}
