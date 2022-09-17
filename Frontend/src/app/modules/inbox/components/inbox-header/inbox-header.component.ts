import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxService } from '../../Services/inbox.service';

@Component({
  selector: 'app-inbox-header',
  templateUrl: './inbox-header.component.html',
  styleUrls: ['./inbox-header.component.css']
})
export class InboxHeaderComponent implements OnInit {
  member_id:any;
 
    
    
  constructor(private router:Router,private inbox_service:InboxService) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.getAllinboxCount(this.member_id);
    this.mySaathidar=false;
    this.myMatches= false;
    this.myInbox= true;
    this.myProfile= false;

    
  }
  //  isMenuOpened: boolean =false;
  isMySaathidarDiv = true;
  isShowDivIf = false;
  isClosedDiv=false;
  navbar=false;
  
  mySaathidar:boolean=false ;
  myMatches:boolean= false;
  myInbox:boolean= true;
  myProfile:boolean= false;

  addEventHandler(){
    // let element = document.getElementsByClassName('ps__rail-y')[0] as HTMLElement;
    // element.addEventListener('click', this.scroll, true);
    this.mySaathidar=true;
    this.myMatches= false;
    this.myInbox= false;
    this.myProfile= false;
    this.router.navigate(['members/dashboard']);
  }
  addEventMatches(){
    // let element = document.getElementsByClassName('ps__rail-y')[0] as HTMLElement;
    // element.addEventListener('click', this.scroll, true);
    this.myMatches=true;
    this.myInbox= false;
    this.myProfile= false;
    this.mySaathidar=false;
    this.router.navigate(['matches/new']);
  }
  addEventInbox(){
    this.myInbox=true;
    this.myMatches=false;
    
    this.myProfile= false;
    this.mySaathidar=false;
    this.router.navigate(['inbox/invitations']);
  }
  addEventProfile(){
    this.myProfile=true;
    this.myInbox=false;
    this.myMatches=false;
    
    this.mySaathidar=false;
    this.router.navigate(['account/setting']);
  }

  scroll(e:Event){
    e.stopPropagation();
  }
  // Togglemenu():void{
  //   this. isMenuOpened = !this. isMenuOpened;
  // }
  // clickOutSide():void{
  //   this. isMenuOpened = !this. isMenuOpened;
  // }
  showDashboard(){
    this.router.navigate(['members/dashboard']);
  }
  
  showMyProfile(){
    this.router.navigate(['members/my-profile']);
  }

  showMyPhotos(){
    this.router.navigate(['members/my-photos']);
  }

  showPartnerPreference(){
    this.router.navigate(['members/partner-preference']);
  }

  showPlans(){
    this.router.navigate(['members/plans']);
  }

  showAccountDetails(){
    this.router.navigate(['members/account']);
  }

  showSearch(){
    this.router.navigate(['member/membersearch']);
  }

  showUpgradeNow(){
    this.router.navigate(['upgrade/plan']);
  }
 
  showHorosscope(){
    this.router.navigate(['members/astro']);
  }
// ************** Inbox Start*********************

showInvitations(){
  this.router.navigate(['inbox/invitations']);
}

showAccepted(){
  this.router.navigate(['inbox/accepted']);
}

showRequests(){
  this.router.navigate(['inbox/requests']);
}

showSent(){
    this.router.navigate(['inbox/sent']);
  }

  logout(): void {
    localStorage.setItem('login_credentials', "");
    localStorage.setItem('login_credentials_email', "");
    localStorage.setItem('setUserName', "");
   this.router.navigate(['']);
  }

  showDeleted(){
    this.router.navigate(['inbox/deleted']);
  }
  showhelp()
{
  this.router.navigate(['account/help'])
}
showBlockmember()
{
  this.router.navigate(['inbox/blockmember']);
}

// ************** Inbox End***********************
// ************** profile Start***********************
showAccount(){
  this.router.navigate(['account/setting']);
}
showContact(){
  this.router.navigate(['account/email-sms']);
}
showHidedeleteprofile(){
  this.router.navigate(['account/HideDeleteProfile']);
}
privacyshow()
{
  this.router.navigate(['account/privacy']) 
}
// ************** profileEnd***********************
// ************** Matches Start***********************

showNewMatches(){
  this.router.navigate(['matches/new']);
}

showMyMatches(){
  this.router.navigate(['matches/my']);
}

showTodaysMatches(){
  this.router.navigate(['matches/todays']);
}

showShortLists(){
  this.router.navigate(['matches/shortlists']);
}
showView()
{
  this.router.navigate(['matches/view']);
}

// ************** Matches End***********************

  toggleisMySaathidarDiv() {
    this.isMySaathidarDiv = !this.isMySaathidarDiv;
    this.isShowDivIf=!this.isShowDivIf;
  }


  viewAllInboxCount:any;

 getAllinboxCount(member_id: any) {
  this.inbox_service.getAllinboxCount(member_id)
  .subscribe(
    results => {
      this.viewAllInboxCount=results;
      // alert(JSON.stringify( this.viewAllInboxCount))
    },
    error => {
      console.log(error);
    });
}

}
