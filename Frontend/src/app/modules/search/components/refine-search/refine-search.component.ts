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
  }

  // veriable declare
  loader = false;
  searchMemberDetails: any;
  member_id: any;
  myJson: any;
  memberIDs:any;

  getFilterRelatedMemberData(searchByJSONDetails:any,member_id: any) {
    this.loader = true;
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
        this.loader = false;
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

  

  
}

