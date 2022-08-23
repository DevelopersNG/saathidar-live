import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
      // ************** profile Start***********************
showAccount(){
  this.router.navigate(['members/account']);
}
showContact(){
  this.router.navigate(['members/contact']);
}
showHidedeleteprofile(){
  this.router.navigate(['members/hide-delete-profile']);
}

// ************** profileEnd***********************

}
