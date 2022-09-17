import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchesService } from '../../services/matches.service';
import { SharingService } from '../../services/sharing.service';

@Component({
  selector: 'app-refine-search',
  templateUrl: './refine-search.component.html',
  styleUrls: ['./refine-search.component.css']
})
export class RefineSearchComponent implements OnInit {
  
  loader = false;
  searchMemberDetails: any;
  searchByJSONDetails: any;
  member_id: any;  
  memberIDs: any;
  imageURL='http://103.174.102.195:8080'
  genderImageURL='/saathidaar/assets/img'
  demoUrlsActive : string[] = [];
  demoUrls:any;
  gender:any;


  myJson: any;



  constructor(private router: Router,
    private route: ActivatedRoute,
    private sharingService: SharingService,
    private matchesServices: MatchesService) {
    this.route.paramMap.subscribe(params => {
      //fetch your new parameters here, on which you are switching the routes and call ngOnInit()
      this.ngOnInit();
    });
  }
  
  ngOnInit(): void {
    this.myJson = this.sharingService.sharingValue;
    this.member_id = localStorage.getItem('login_credentials');
    this.getFilterRelatedMemberData(JSON.parse(this.myJson),this.member_id);
    // start
    this.gender = localStorage.getItem('login_credentials_gender');

    if(this.gender=='male' || this.gender=='Male'){
      this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
    }
    if(this.gender=='female' || this.gender=='Female'){
      this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
    }
    // end

  }

  // ****************************************** STart
  profileIMags:any;
  demoUrlsModal:any;
  callIMageForMemberID(member_id:any){

    this.profileIMags=null;
    this.demoUrlsModal=null;
    this.seturl=null;
    this.matchesServices.getDataImageDataByURL(member_id)
    .subscribe((results : any) => {
      if(results.data.member_images!='undefined'){
      this.profileIMags=results.data[0].member_images;
      this.demoUrlsModal=results.data;
    }
     });
  }

  // *********************************************** End
  
  
  getFilterRelatedMemberData(searchByJSONDetails:any,member_id: any) {
    this.matchesServices.searchMemberDetails(searchByJSONDetails, member_id)
      .subscribe(
        results => {

          this.searchMemberDetails =results;

          //  next and previous- start
          this.memberIDs = [];
          for (var i = 0; i < this.searchMemberDetails.length; i++) {
            this.memberIDs.push(this.searchMemberDetails[i]['member_id']);
        }
        // this.sharingService.sharingValue=this.memberIDs;  
        sessionStorage.setItem("value",  JSON.stringify(this.memberIDs));
                  //  next and previous- end  
        // this.loader = false;
        },
        error => {
          console.log(error);
        });
  }

  singleViewMemberDetails(member_id:string){
    this.router.navigate(['/members/profile/'+member_id]);
  }

  sentRequests(member_to_id:string)
  {
const data={
  request_from_id: this.member_id ,
  request_to_id: member_to_id,
  request_status:"Pending"
}

this.matchesServices.sentRequests(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if(results!=null){
            this.getFilterRelatedMemberData(JSON.parse(this.myJson),this.member_id);
        }
        },
        error => {
          this.loader = false;
          console.log(error);
        });

    // alert(JSON.stringify(data));
  }

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
  // ************************************block*************************
  
  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }

    this.matchesServices.sentShortListData(data)
      .subscribe(
        results => {
          // if requests are send successfully then call member details again
          if (results != null) {
            this.callSearchDetails(this.member_id);
          }
        },
        error => {
          this.loader = false;
          console.log(error);
        });
  }

  callSearchDetails(member_id: any) {
    this.loader = true;
    this.matchesServices.searchMyMatchesAllMember( member_id)
      .subscribe(
        results => {

          // alert(JSON.stringify(results.data))
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


  
blockmemberID(member_to_id: string) {

  const data = {
    request_from_id:member_to_id,
    request_to_id:this.member_id,
    block_by_id:this.member_id,
    block_status:"Block"
  }
 
  this.matchesServices.blockmember(data)
    .subscribe(
      results => {
        // alert(JSON.stringify(results))
      },
      error => {
        console.log(error);
      });
}

  
}

