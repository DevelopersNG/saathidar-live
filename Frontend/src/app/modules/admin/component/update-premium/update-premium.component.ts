import { Component, OnInit } from '@angular/core';
import { LandingPageService } from 'src/app/services/landing-page.service';
import { Router } from '@angular/router';
import { AdminPremium, AdminPremiumEdit } from '../../admin-premium.model';
import { inArray } from 'jquery';


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
    discount_amount: '',
    plan_details_five: '',
    plan_details_six: '',
    plan_details_seven: '',
    plan_details_eight: ''
  };
  adminPremiumEdit:AdminPremiumEdit ={
    plan_name_edit: '',
    plan_Amount_edit: '',
    plan_month_edit: '',
    plan_details_one_edit: '',
    plan_details_Two_edit: '',
    plan_details_Three_edit: '',
    plan_details_four_edit: '',
    plan_details_five_edit: '',
    plan_details_six_edit: '',
    plan_details_seven_edit: '',
    plan_details_eight_edit: '',
    plan_name:'',
  }
  setfeaturesNameByid:any[]=[];
  AddPlan()
  {
  let features_value='';
  if(this.AdminPremium.plan_details_one!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_one;
  }
  if(this.AdminPremium.plan_details_Two!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_Two;
  }
  if(this.AdminPremium.plan_details_Three!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_Three;
  }
  if(this.AdminPremium.plan_details_four!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_four;
  }
  if(this.AdminPremium.plan_details_five!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_five;
  }
  if(this.AdminPremium.plan_details_six!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_six;
  }
  if(this.AdminPremium.plan_details_seven!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_seven;
  }
  if(this.AdminPremium.plan_details_eight!=''){
    features_value=features_value+","+this.AdminPremium.plan_details_eight;
  }
  features_value=features_value.replace(features_value.charAt(0), "");

    const data ={
      plan_name:this.AdminPremium.plan_name,
      plan_price:this.AdminPremium.plan_Amount,
      plan_validity:this.AdminPremium.plan_month,
      plan_discount:this.AdminPremium.discount_percuntage,
      discount_price:this.AdminPremium.discount_amount,
      feature_name:features_value
    }
    this.landingPageServices.planAddAdmin(data)
      .subscribe(
        results => {
          // window.location.reload();
        },
        error => {
          console.log(error);
        });
  }

  getPercantageValue(plan_amount:any,per:any){
    let value= (plan_amount/100) * per;
    this.AdminPremium.discount_amount=plan_amount-value;
  }


  features_name:any;
  features_id:any;

  premiumCardEdit(feature:any): void
  {
    const data = {
      feature_name:feature.features_name
    }
    this.landingPageServices.premiumCardAdd(data,feature.features_id)
    .subscribe(
      results => {
      },
      error => {
        console.log(error);
      });
  }

  addNameother(plans:any)
  {
    const data ={
      id:plans.plan_id,
      plan_name:plans.plan_name,
      plan_price:plans.plan_price,
      plan_validity:plans.plan_validity
    }

    this.landingPageServices.addNameotherDetails(data)
      .subscribe(
        results => {
          window.location.reload();
        },
        error => {
          console.log(error);
        });
  }
  planDelet(plans:any)
  {
    const data ={
      id:plans.plan_id,
    }
    this.landingPageServices.planDelete(data,plans.plan_id)
      .subscribe(
        results => {
          window.location.reload();
          
        },
        error => {
          console.log(error);
        });
  }

    getMemberPlansDetails(){
      this.landingPageServices.getMemberPlansDetails()
        .subscribe(
          results => {
            this.planDetails = results.data;
          },
          error => {
            console.log(error);
          });
    }
    YesfeatureSet:any;
    Yesfeature (features_id:any){
      const data ={
        features_id:features_id
      }
      this.landingPageServices.YesValidPlanfeature(features_id,data)
        .subscribe(
          results => {
            window.location.reload();
            this.YesfeatureSet= results.data;
          },
          error => {
            console.log(error);
          });
    }

    Nofeature(features_id:any)
    {
      const data ={
        features_id:features_id
      }
      this.landingPageServices.NoValidPlanfeature(features_id,data)
        .subscribe(
          results => {
            window.location.reload();
          },
          error => {
            console.log(error);
          });
    }

   

}
