import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updgrade-now',
  templateUrl: './updgrade-now.component.html',
  styleUrls: ['./updgrade-now.component.css']
})
export class UpdgradeNowComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  } 

  callPayment( plan_name:any ,plan_amount:any){
    this.router.navigate(['members/payment/'+plan_name+'/'+plan_amount]);
  }

}
