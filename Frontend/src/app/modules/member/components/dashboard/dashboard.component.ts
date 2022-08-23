import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
}) 
export class DashboardComponent implements OnInit {
 member_id:any;
 viewAllCount:any;
 searchMemberDetails: any;
 premiumMemberDetails: any;
 memberIDs: any;
 loader = false;
 imageURL='http://103.150.186.33:8080'
 
  constructor(private router:Router,
    private searchService: SearchService) { }

    // demoUrls : string[] = [];
    // demoUrlsActive : string[] = [];
    demoUrls : any;
    demoUrlsActive :any;

    seturl:any;
    loadTIme:boolean=false;
    name = 'Test display image';
    thumbnail: any;

    fullname:any;
    email:any;
    phone:any;
    profile_id:any;
    gender:any;
    genderImageURL='/saathidaar/assets/img'
  ngOnInit(): void {
    
    this.fullname=localStorage.getItem('login_credentials_firstname') + " "+ localStorage.getItem('login_credentials_lastname');
    this.email=localStorage.getItem('login_credentials_email');
    this.phone=localStorage.getItem('login_credentials_phone');
    this.profile_id=localStorage.getItem('login_credentials_profile_id');
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
        // alert(JSON.stringify($(this)))
        $('img').removeClass("imageb");
        $(this).addClass("imageb");
    });
    });
    
    
    this.member_id = localStorage.getItem('login_credentials');
    
   
    this.getAllCount(this.member_id); 
  
    this.callSearchDetails(this.member_id);  
    this.callNewPreiumMatchesDetails(this.member_id);
    this.callIMageForMemberID(this.member_id)



  }
       
  

  // ****************************************** STart
  profileIMags:any;
  callIMageForMemberID(member_id:any){
    this.searchService.getDataImageDataByURL(this.member_id)
    .subscribe((results : any) => {
     
      this.profileIMags=results.data[0].member_images;
      this.demoUrls=results.data;
      // alert(JSON.stringify(this.demoUrls))

     });
  }
  // *********************************************** End
  


 
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

 getAllCount(member_id: any) {
  this.searchService.getAllCount(member_id)
  .subscribe(
    results => {
      this.viewAllCount=results;
      // alert(JSON.stringify( this.viewAllCount))
    },
    error => {
      console.log(error);
    });
}


callAcceptRequest(){
  this.router.navigate(['/inbox/accepted']);
}

callSendRequest(){
  this.router.navigate(['/inbox/sent']);
}
callrecentvisitors(){
  this.router.navigate(['/matches/view']);
}
// **************************new***************************
singleViewMemberDetails(member_id: string) {
  this.router.navigate(['/members/profile/' + member_id]);

}
callSearchDetails(member_id: any) {
  this.loader = true;
  this.searchService.searchNewMatchesAllMember(member_id)
    .subscribe(
      results => {
        // alert(JSON.stringify(results.data))
        
        this.searchMemberDetails = results.data;
        // this is for next and previous- start
        this.memberIDs = [];
        for (var i = 0; i < this.searchMemberDetails.length; i++) {
          this.memberIDs.push(this.searchMemberDetails[i]['member_id']);
        }
        sessionStorage.setItem("value", JSON.stringify(this.memberIDs));
        // this is for next and previous- end  
        this.loader = false;
      },
      error => {
        this.loader = false;
        console.log(error);
      });
}

callNewPreiumMatchesDetails(member_id:any){
  this.loader = true;
  this.searchService.callNewPreiumMatchesDetails(member_id)
    .subscribe(
      results => {
    //  alert(results.data)
    // alert(JSON.stringify(results.data))
        this.premiumMemberDetails = results.data;
        // this is for next and previous- start
        this.memberIDs = [];
        for (var i = 0; i < this.premiumMemberDetails.length; i++) {
          this.memberIDs.push(this.premiumMemberDetails[i]['member_id']);
        }
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