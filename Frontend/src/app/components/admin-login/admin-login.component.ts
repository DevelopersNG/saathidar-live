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
  showMasseage=false

  Adminlogin(): void {
    const data = {
      username: this.adminLoginModel.usernameAdmin,
      password: this.adminLoginModel.passwordAdmin
    };
    this.landingPageServices.AdminUserLogin(data)
    .subscribe(
      results => {
        if(results='')
        {
          this.showMasseage=true
        }
      this.router.navigate(['/admin/setting']);
      },
      error => {
        console.log(error);
      });
    }

  // Adminlogin()
  // {
  //   this.router.navigate(['/admin/setting']);
  // }

    getAdmindetataiLogin:any
  getAdminDetails(data:any){
    this.landingPageServices.getAdminDetails(data)
    .subscribe(
      (results: any) => {
        this.getAdmindetataiLogin =results.data;
      },
      (      error: any) => {
        console.log(error);
      });
  }
}
