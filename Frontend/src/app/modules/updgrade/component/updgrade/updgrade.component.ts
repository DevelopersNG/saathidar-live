import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { SharingService } from '../../services/sharing.service';

@Component({
  selector: 'app-updgrade',
  templateUrl: './updgrade.component.html',
  styleUrls: ['./updgrade.component.css']
})
export class UpdgradeComponent implements OnInit {
  planDetails:any;
  constructor(private router:Router, 
    private searchService:SearchService,
    private sharingService: SharingService) { }
  ngOnInit(): void {
    this.getMemberPlansDetails();
  }
  callPayment( plan_name:any ,plan_amount:any){
    this.sharingService.sharingValue=plan_amount;
    // this.router.navigate(['members/payment/'+plan_name+'/'+plan_amount]);
    this.router.navigate(['upgrade/payment-pay/'+plan_name]);
  }
  getMemberPlansDetails() {
    this.searchService.getMemberPlansDetails()
      .subscribe(
        results => {
      // alert(JSON.stringify(results))
          this.planDetails = results.data;
        },
        error => {
          console.log(error);
        });
  }
}
