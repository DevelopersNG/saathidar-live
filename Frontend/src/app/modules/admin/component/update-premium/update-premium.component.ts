import { Component, OnInit } from '@angular/core';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { Router } from '@angular/router';
import { AdminPremium, AdminPremiumEdit } from '../../admin-premium.model';


@Component({
  selector: 'app-update-premium',
  templateUrl: './update-premium.component.html',
  styleUrls: ['./update-premium.component.css']
})
export class UpdatePremiumComponent implements OnInit {
  planDetails:any;
  constructor(private landingPageServices: LandingPageService, private router: Router,) { }

  ngOnInit(): void {
    this.getMemberPlansDetails();
  }

  AdminPremium: AdminPremium = {
    plan_name: '',
    plan_Amount: '',
    plan_month: '',
    plan_details_one: '',
    plan_details_Two: '',
    plan_details_Three: '',
    plan_details_four: '',
    discount_percuntage: '',
    discount_amount: ''
  };

  AdminPremiumEdit:AdminPremiumEdit ={
    plan_name_edit: '',
    plan_Amount_edit: '',
    plan_month_edit: '',
    plan_details_one_edit: '',
    plan_details_Two_edit: '',
    plan_details_Three_edit: '',
    plan_details_four_edit: ''
  }
  premiumCardEdit (): void
  {
    const data = {
      plan_name_edit: this.AdminPremiumEdit.plan_name_edit,
      plan_Amount_edit: this.AdminPremiumEdit.plan_Amount_edit,
      plan_month_edit:this.AdminPremiumEdit.plan_month_edit,
      plan_details_Two_edit:this.AdminPremiumEdit.plan_details_Two_edit,
      plan_details_Three_edit:this.AdminPremiumEdit.plan_details_Three_edit,
      plan_details_four_edit:this.AdminPremiumEdit.plan_details_four_edit,
    }
    alert(JSON.stringify(data))
    this.landingPageServices.premiumCardAdd(data)
    .subscribe(
      results => {
    
      },
      error => {
        console.log(error);
      });
  }
  premiumCardAdd(): void {
    const data = {
      plan_name: this.AdminPremium.plan_name,
      plan_Amount: this.AdminPremium.plan_Amount,
      plan_details_one:this.AdminPremium.plan_details_one,
      plan_details_Two:this.AdminPremium.plan_details_Two,
      plan_details_Three:this.AdminPremium.plan_details_Three,
      plan_details_four:this.AdminPremium.plan_details_four,
      discount_percuntage:this.AdminPremium.discount_percuntage,
      discount_amount:this.AdminPremium.discount_amount


    }
    alert(JSON.stringify(data))
    this.landingPageServices.premiumCardAdd(data)
    .subscribe(
      results => {
      },
      error => {
        console.log(error);
      });
    }
    getMemberPlansDetails() {
      this.landingPageServices.getMemberPlansDetails()
        .subscribe(
          results => {
            alert(JSON.stringify(results.data))
            this.planDetails = results.data;
          },
          error => {
            console.log(error);
          });
    }
}
