import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SharingService } from 'src/app/modules/updgrade/services/sharing.service';
import { SearchService } from '../../services/search.service';

declare var Razorpay: any;

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  private routeSub: Subscription;
  constructor(private route: ActivatedRoute,
    private searchService: SearchService,
    private router:Router,
    private sharingService: SharingService) {}

  plan_name: string;
  plan_amount: number;
  member_id:any;
  member_email_id:any;
  member_phone:any;
  member_first_name:any;
  member_last_name:any;
  planDetails:any;

  ngOnInit(): void {
    this.member_id=localStorage.getItem('login_credentials');
    this.member_email_id=localStorage.getItem('login_credentials_email');
    this.member_phone=localStorage.getItem('login_credentials_phone');
    this.member_first_name=localStorage.getItem('login_credentials_firstname');
    this.member_last_name=localStorage.getItem('login_credentials_lastname');
    
    this.routeSub = this.route.params.subscribe(params => {
      this.plan_name = params['plan_name'];
      this.plan_amount = this.sharingService.sharingValue;
      // this.plan_amount = params['plan_amount'];
      
    })
  }


  

  message: any = "Not yet stared";
  paymentId = "";
  error = "";
  title = 'angular-razorpay-intergration';
  options = {
    "key": "rzp_test_QHOsVAlUo7NNwl",
    "amount": "300",
    "name": "saathidar.com",
    "description": "Web Development",
    "image": "https://www.abhijitgatade.com/assets/img/favicon.png",
    // "order_id": "order_JiZPT9i145Ltol", for only live mode
    "handler": function (response: any) {
      var event = new CustomEvent("payment.success",
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );
      window.dispatchEvent(event);
    },
    "prefill": {
      "name": "",
      "email": "",
      "contact": ""
    },
    "notes": {
      "address": ""
    },
    "theme": {
      "color": "#3399cc"
    }
  };


  initPay() {
    this.paymentId = '';
    this.error = '';
    this.options.amount =""+ this.plan_amount * 100; //paise
    this.options.prefill.name = "vikas";
    this.options.prefill.email = this.member_email_id;
    this.options.prefill.contact = this.member_phone;
    var rzp1 = new Razorpay(this.options);
    rzp1.open();
    rzp1.on('payment.failed', function (response: any) {
      //this.message = "Payment Failed";
      // Todo - store this information in the server
      alert(response.razorpay_payment_id);
      console.log(response.error.code);
      console.log(response.error.description);
      console.log(response.error.source);
      console.log(response.error.step);
      console.log(response.error.reason);
      console.log(response.error.metadata.order_id);
      console.log(response.error.metadata.payment_id);
      //this.error = response.error.reason;
    });

    rzp1.on('payment.success', function (response: any) {
      alert(JSON.stringify(response))
    });
  }

  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: any): void {
    // this.message = "Success Payment";
    
    const data={
      plan_name: this.plan_name,
      plan_amount:this.plan_amount,
      member_id:this.member_id
    }


    this.searchService.savePremiumMember(data)
    .subscribe(
      results => {
        this.message = "Success Payment";
      },
      error => {
        console.log(error);
      });




  }
}
