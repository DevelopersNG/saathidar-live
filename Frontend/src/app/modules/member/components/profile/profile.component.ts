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

  seturl: any;
  loadTIme: boolean = false;
  change() {
    this.loadTIme = false;
  }
  showimage(url: any) {
    this.seturl = url;
    this.loadTIme = true;
  }

  check(val: any) {
    // alert(val)
  }

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
    private router: Router) {
  }

  // demoUrls: string[] = [];
  // demoUrlsActive: string[] = [];

  demoUrls: any;
  demoUrlsActive: any;

  item: any;
  len: any;
  ngOnInit(): void {
    $(document).ready(function () {
      $(function () {
        $('.hidden-menu').slideToggle("slow");
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
      this.setMemberDetailsByMemberID(this.member_id);
      this.setPartnerPreference(this.member_id, this.login_id);
    });


  
    this.getIMagesForMembers(this.member_id);
  }

  getIMagesForMembers(member_id: any) {
    this.searchServices.getData(this.member_id)
    .subscribe((results : any) => {
      // alert(JSON.stringify(results))
      console.log(results)
      // this.demoUrls=baseImage.data[0].member_images;
      this.demoUrls=results.data;
        // this.demoUrls.push(results);
      // alert(this.demoUrls)
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

          alert(JSON.stringify(results.data))
          this.memberDetails = results.data;
          console.log(this.memberDetails);
          
        },
        error => {
          console.log(error);
        });
  }


  setPartnerPreference(member_id: number, login_id: number) {
    this.searchServices.getPartnerPreference(member_id, login_id)
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          // alert(this.preferenceDetails )
          this.preferenceDetails = results;
          
          // this.preferenceDetails.my_annual_income="NO";
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

