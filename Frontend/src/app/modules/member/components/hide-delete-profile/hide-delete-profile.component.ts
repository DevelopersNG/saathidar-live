import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-hide-delete-profile',
  templateUrl: './hide-delete-profile.component.html',
  styleUrls: ['./hide-delete-profile.component.css']
})
export class HideDeleteProfileComponent implements OnInit {

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

}
