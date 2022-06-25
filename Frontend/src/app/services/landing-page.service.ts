import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginModel } from '../models/login-model.model';


const baseUrl = 'http://localhost:9094/api';
// const baseUrl = 'http://192.168.1.37:9094/api';

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

}
