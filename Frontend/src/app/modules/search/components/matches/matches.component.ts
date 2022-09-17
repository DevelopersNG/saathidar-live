import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { SharingService } from '../../services/sharing.service';
import { MatchesService } from '../../services/matches.service'; 

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {
  // searchMemberDetailsView(data: string) {
  //   this.searchMemberDetails = JSON.stringify(data);
  // }
  loader = false;
  searchMemberDetails: any;
  searchByJSONDetails: any;
  member_id: any;

  constructor(private landingPageServices: LandingPageService,
    private router: Router,
    private sharingService: SharingService,private matchesService: MatchesService) {
  }

  demoUrlsActive : string[] = [];
  demoUrls:any;
  imageURL='http://103.174.102.195:8080'
  gender:any;
  genderImageURL='/saathidaar/assets/img'
  ngOnInit(): void {
    // this.data= localStorage.getItem('filter_data');
    // alert(localStorage.getItem("homepage_search_details"));
    this.searchByJSONDetails = localStorage.getItem("homepage_search_details");
    this.member_id = localStorage.getItem('login_credentials');
        // start
        this.gender = localStorage.getItem('login_credentials_gender');
     
        if(this.gender=='male' || this.gender=='Male'){
          this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
        }
        if(this.gender=='female' || this.gender=='Female'){
          this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
        }
        // end
    this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
    $(document).ready(function(){
      $('img').click(function(){
        // alert(JSON.stringify($(this)))
        $('img').removeClass("imageb");
        $(this).addClass("imageb");
    });
    });
  }
  // ****************************************** STart
  profileIMags:any;
  demoUrlsModal:any;
  callIMageForMemberID(member_id:any){

    this.profileIMags=null;
    this.demoUrlsModal=null;
    this.seturl=null;
    this.matchesService.getDataImageDataByURL(member_id)
    .subscribe((results : any) => {
      if(results.data.member_images!='undefined'){
      this.profileIMags=results.data[0].member_images;
      this.demoUrlsModal=results.data;
    }
     });
  }
  // *********************************************** End
  religionList: string;
  List: string;
  memberIDs: any;
  callSearchDetails(searchByJSONDetails: any, member_id: any) {
    this.loader = true;
    this.landingPageServices.searchMemberDetails(searchByJSONDetails, member_id)
      .subscribe(
        results => {
          // alert(JSON.stringify(results))
          this.searchMemberDetails = results;
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
  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }
  sentRequests(member_to_id: string) {
    const data = {
      request_from_id: this.member_id,
      request_to_id: member_to_id,
      request_status: "Pending"
    }
    this.landingPageServices.sentRequests(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if (results != null) {

            this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });

    // alert(JSON.stringify(data));
  }

  addToShortList(member_to_id: string) {
    
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.landingPageServices.sentShortListData(data)
      .subscribe(
        results => {
       
          // if requests are send successfully then call member details again
          if (results != null) {
             

            this.callSearchDetails(JSON.parse(this.searchByJSONDetails), this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });
  }
    // ***************************************new line modal**********************************

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
      blockmemberID(member_to_id: string) {

        const data = {
          request_from_id:member_to_id,
          request_to_id:this.member_id,
          block_by_id:this.member_id,
          block_status:"Block"
        }
       
        this.matchesService.blockmember(data)
          .subscribe(
            results => {
              // alert(JSON.stringify(results))
            },
            error => {
              console.log(error);
            });
      }
      
      
}
