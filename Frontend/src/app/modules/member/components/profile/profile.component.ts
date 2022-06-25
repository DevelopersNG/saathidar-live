import { Component, OnInit } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SharingService } from 'src/app/modules/search/services/sharing.service';
import { SearchService } from '../../services/search.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  dots: any;
  moreText: any;
  btnText: any;
  member_id: number;
  memberDetails: any;
  preferenceDetails: any;
  login_id: any;
  private routeSub: Subscription;

  constructor(private route: ActivatedRoute,
    private searchServices: SearchService,
    private sharingService: SharingService,
    private router:Router) {

      
     }

  item:any;
  len:any;
    ngOnInit(): void {
       $(document).ready(function() {
      $(function() {
        $('.hidden-menu').slideToggle("slow");
   
      });
    });
     

      
    //  jQuery
    // $(document).ready(function() {
    //   $('#show-hidden-menu').click(function() {
    //     $('.hidden-menu').slideToggle("slow");
   
    //   });
    // });



    this.routeSub = this.route.params.subscribe(params => {
      this.member_id = params['member_id'];


      // value are getting for next and previous start
      var number=sessionStorage.getItem("value" || "[]");
      
      if(number==null)
      {
        this.item=[];
      }else{
        this.item=JSON.parse(number);
      }
      
      // alert(this.item)
      if(this.item==null){
        // this.item= this.sharingService.sharingValue;
        this.item= sessionStorage.getItem("value");
      }

        for(var k=0;k<this.item.length;k++){
          if(this.item[k]==this.member_id){
            this.i=k;
          }
        }
        alert( this.item)
            // value are getting for next and previous end
        this.login_id = localStorage.getItem('login_credentials');     
        this.setMemberDetailsByMemberID(this.member_id);
        this.setPartnerPreference(this.member_id, this.login_id);
    });
  }




  i:any;
  
  previous() {
   var len = this.item.length;
   var  current = this.item[this.i];
   var  previous = this.item[(this.i+len-1)%len];
   this.member_id=previous;
   this.router.navigate(['members/profile/'+previous]);
  }

  Next() { 
    alert()
    var len = this.item.length;
    var  current = this.item[this.i];
    // alert(this.item)
    var next = this.item[(this.i+1)%len];
    this.member_id=next;
    this.router.navigate(['members/profile/'+next]);
  }



  setMemberDetailsByMemberID(member_id: number) {
    this.searchServices.getMemberDetailsByMemberID(member_id,this.login_id)
      .subscribe(
        results => {
     
          this.memberDetails = results;
          alert(JSON.stringify(this.memberDetails))
        },
        error => {
          console.log(error);
        });
  }


  setPartnerPreference(member_id: number, login_id: number) {
    this.searchServices.getPartnerPreference(member_id, login_id)
      .subscribe(
        results => {
          // alert(this.preferenceDetails )
          this.preferenceDetails = results;
        },
        error => {
          console.log(error);
        });
  }


  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

}
function x(x: any, arg1: (any: any) => boolean) {
  throw new Error('Function not implemented.');
}

