import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginModel } from '../models/login-model.model';

//loval server - akanksha
// const baseUrl = 'http://192.168.1.34:8080/api';

//loval server - vikas pc
// const baseUrl = 'http://103.150.186.33:8080/api';

// live server
const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
// const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'

  
@Injectable({
  providedIn: 'root'
})
export class LandingPageService {

  constructor(private http: HttpClient) { }

  doLogin(data: any): Observable<any> {
    return this.http.post(baseUrl+"/users/login", data);
  }
  
  getMemberIDByUserLogin(userid: any): Observable<any> {
    return this.http.get(baseUrl+"/get-member-id-by-login/"+userid);
  }
  
  sendOtpForMemberRegistration(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/otp",data);
  }

  verifyOTP(otp: string,phone: string): Observable<any> {
    return this.http.get(baseUrl+"/member/verify/otp/"+otp+"/"+phone);
  }

  shortMemberRegistrations(data: any): Observable<any> {
    return this.http.post(baseUrl+"/users/register",data);
  }

  getMemberPlansDetails(): Observable<any> {
    return this.http.get(baseUrl+"/member/plans-details");
  }

  searchMemberDetails(data: any,memberID: any): Observable<any> {
    return this.http.post(baseUrl+"/member/get-all-member/"+memberID,data);
  }

  getReligionName(): Observable<any> {
    return this.http.get(baseUrl+"/get/religion-name");
  }
  // get-details/{id}

  // ********************* requests services *************************

  sentRequests(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/send-request",data);
  }

  // ****************************** Short Lists *************************

  sentShortListData(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/add-to-shortlist",data);
  }


  // **********************************Admin API******************************
   
  Adminlogin(data:any): Observable<any> {
    return this.http.post(baseUrl+"/logout", data);
  }
  getAdminDetails(data:any): Observable<any> {
    return this.http.get(baseUrl+"/get-member-id-by-login/"+data);
  }

  uploadImagesAdmin(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/upload/photo", data);
  }

  getImageSuccessStory(data:any): Observable<any> {
    return this.http.get(baseUrl+"/member/app/get/photo/"+data);
   }

   deleteStory(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/upload/photo", data);
  }

  premiumCardAdd(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/upload/photo", data);
  }

  sendOTPForgotpassword(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/forgot/password/otp", data);
  }

  saveForgotPassword(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/forgot/password/update", data);
  }
}
