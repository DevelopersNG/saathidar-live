import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminLoginModel } from 'src/app/models/login-model.model';
import { LandingPageService } from 'src/app/services/landing-page.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  constructor(private landingPageServices: LandingPageService, private router: Router,) { }

  ngOnInit(): void {
  }
  adminLoginModel: AdminLoginModel = {
    usernameAdmin: '',
    passwordAdmin: '',
    detailsSuccessStoris: '',
  
  };


  // Adminlogin(): void {
  //   const data = {
  //     username: this.adminLoginModel.usernameAdmin,
  //     password: this.adminLoginModel.passwordAdmin
  //   };
  //   alert(JSON.stringify(data))
  //   this.landingPageServices.Adminlogin(data)
  //   .subscribe(
  //     results => {
  //     this.router.navigate(['/admin/setting']);
  //     },
  //     error => {
  //       console.log(error);
  //     });
  //   }

  Adminlogin()
  {
    this.router.navigate(['/admin/setting']);
  }

    getAdmindetataiLogin:any
  getAdminDetails(data:any){
    this.landingPageServices.getAdminDetails(data)
    .subscribe(
      (results: any) => {
        // alert(JSON.stringify(results))
  
        this.getAdmindetataiLogin =results.data;
      
  
      },
      (      error: any) => {
        console.log(error);
      });
  }
}
