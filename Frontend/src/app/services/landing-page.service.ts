import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginModel } from '../models/login-model.model';

//loval server - akanksha
// const baseUrl = 'http://192.168.1.35:8088/api';
//loval server - vikas pc
// const baseUrl = 'http://103.150.186.33:8080/api';
// live server
// const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
const baseUrl = 'http://103.174.102.195:8080/saathidaar_backend/api' 

  
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

  AdminUserLogin(data:any): Observable<any> {
    return this.http.post(baseUrl+"/admin-login",data);
  }
  
  sendOtpForMemberRegistration(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/otp",data);
  }

  verifyOTP(otp: string,phone:string): Observable<any> {
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
// ******************************avd*******************************
  uploadImagesAdv(data:any): Observable<any> {
    return this.http.post(baseUrl + "/admin/advertisement/add/advt_photo", data);
  }
  getImageAvd(): Observable<any> {
    return this.http.get(baseUrl+"/admin/advertisement/app/get");
   }

   deleteAvd(data:any,image_id:any): Observable<any> {
    return this.http.post(baseUrl + "/admin/advertisement/delete/advt_photo/"+image_id,data);
  }

// ******************************avd end*******************************
// *****************************  story **************************

  uploadImagesSuccessStoris(data:any): Observable<any> {
    return this.http.post(baseUrl + "/admin/success/story/add", data);
  }

  getImageSuccessStory(): Observable<any> {
    return this.http.get(baseUrl+"/admin/success/story/get");
   }

   deletestory(data:any,image_id:any): Observable<any> {
    return this.http.post(baseUrl + "/success/story/delete/"+image_id,data);
  }
// *****************************  story end**************************

  // ********************** URL stort landing Image **********************************************44
  getDataImageStory(): Observable<any> {
    return this.http.get(baseUrl+"/member/app/get/photo/");
   }

  sendOTPForgotpassword(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/forgot/password/otp", data);
  }
  saveForgotPassword(otp: string,phone:string): Observable<any> {
    return this.http.get(baseUrl + "/member/verify/otp/email/"+otp+"/"+phone);

  }
  sendEmail(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/forgot/password/update", data);
  }
  
  // +++++++++++++++++++++++++++++++++basic details form member*****************************
  BasicFormDetails(user_id:any,data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/short-registration/update/"+user_id,data);
  }
  getReligionNameBasic(): Observable<any> {
    return this.http.get(baseUrl + "/get/religion-name");
  }
  getCountryName(): Observable<any> {
    return this.http.get(baseUrl + "/get/country");
  }
  getCastNameBYReligionName(religion_name: string): Observable<any> {
    return this.http.get(baseUrl + "/get/cast-name/by/religion_name/" + religion_name);
  }
// (****************************  plan fachuers***********************************)

YesValidPlanfeature(feature_id:any,data:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/features/valid/"+feature_id,data);
}

NoValidPlanfeature(feature_id:any,data:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/features/invalid/"+feature_id,data);
}

premiumCardAdd(data:any,features_id:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/features/update/"+features_id,data);
}
addNameotherDetails(data:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/update",data);
}
planDelete(data:any,plan:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/delete/"+plan,data);
}

planAddAdmin(data:any): Observable<any> {
  return this.http.post(baseUrl + "/admin/plan/add/",data);
}
}
