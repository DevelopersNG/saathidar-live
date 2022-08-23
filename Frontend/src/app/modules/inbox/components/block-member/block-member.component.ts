import { Component, OnInit } from '@angular/core';
import { state } from '@angular/animations';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { InboxService } from '../../Services/inbox.service';
import { MatchesService } from 'src/app/modules/search/services/matches.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';



@Component({
  selector: 'app-block-member',
  templateUrl: './block-member.component.html',
  styleUrls: ['./block-member.component.css']
})
export class BlockMemberComponent implements OnInit {

  constructor(private inbox_service:InboxService,private matchesService:MatchesService, private router:Router) { }
  member_id:any;
  demoUrls:any;
  demoUrlsActive : string[] = [];
  loader = false;
  invitationsDetails:any=0; 
  
  seturl:any;
  loadTIme:boolean=false;
  gender:any;
  genderImageURL='/saathidaar/assets/img'
  blockDetails:any;
imageURL='http://103.150.186.33:8080'

  ngOnInit(): void {

    this.member_id = localStorage.getItem('login_credentials');
    this.blockhMemberDetails(this.member_id)
   // start
 this.gender = localStorage.getItem('login_credentials_gender');

 if(this.gender=='male' || this.gender=='Male'){
  this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
}
if(this.gender=='female' || this.gender=='Female'){
  this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
}
// end
    
    $(document).ready(function(){
      $('img').click(function(){
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
    this.inbox_service.getDataImageDataByURL(member_id)
    .subscribe((results : any) => {
      if(results.data.member_images!='undefined'){
      this.profileIMags=results.data[0].member_images;
      this.demoUrlsModal=results.data;
    }
     });
  }

  // 

  change(){
    this.loadTIme=false;
  }
  showimage(url:any)
  {
   this.seturl=url;
   this.loadTIme=true;
  }
  
  check(val:any){
    }


  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }
  addToShortList(member_to_id: string) {
    const data = {
      shortlist_from_id: this.member_id,
      shortlist_to_id: member_to_id,
      shortlist_status: "add"
    }
    this.matchesService.sentShortListData(data)
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
  unBlockMember(member_to_id: string) {
    const data = {
      request_from_id:member_to_id,
      request_to_id:this.member_id,
      block_by_id:this.member_id,
      block_status:"Un-Block"
    }
    this.inbox_service.blockmember(data)
      .subscribe(
        results => {
          // alert(JSON.stringify(results))
          window.location.reload();

        },
        error => {
          console.log(error);
          // window.location.reload();

        });
  }
//  *************************************** demo block member***********************************
searchMemberDetails:any;
memberIDs:any;
callSearchDetails(member_id: any) {
  this.loader = true;
  this.matchesService.searchNewMatchesAllMember( member_id)
    .subscribe(
      results => {
    //  alert(results.data)
                          
    // alert( JSON.stringify(results.data[0].images[0].member_images));         
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



blockhMemberDetails(member_id:any){

  this.inbox_service.blockhMemberDetails(member_id)
  .subscribe(
    results => {
      // alert(JSON.stringify(results.data))
      this.blockDetails = results.data;
    },
    error => {
      console.log(error);
    });

}

}
