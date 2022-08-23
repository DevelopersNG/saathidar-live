import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


// const baseUrl = 'http://localhost:9094/api';
// const baseUrl = 'http://192.168.1.38:9094/api';
// const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"
// const baseUrl = 'http://103.150.186.33:8080/kidzee_server/api'
const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'

@Injectable({
  providedIn: 'root'
})
export class SettingService {

  constructor(private http: HttpClient) { }

  changePassword(data: any): Observable<any> {
    return this.http.post(baseUrl+"/users/changepwd",data);
  }
  saveProfile(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/hide/",data);
  }


  Activate(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/activate",data);
  }

  ActivateData(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/get/activate/"+member_id);
  }


  saveprofiledata(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/get/activate/"+member_id);
  }


  // ************* Privacy Options ***********************

  getAllPrivacyDetails(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/privacy/get/all/"+member_id);
  }

  updatePhonePrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/phone",data);
  }

  updatephotoPrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/photo",data);
  }
  
  
  updateEmailPrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/email",data);
  }

  updateDOBPrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/dob",data);
  }
  updateAnnualIncomePrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/annual-income",data);
  }
  updateHoroscopePrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/horoscope",data);
  }
  updateAccountVisitorsPrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/visitors-settings",data);
  }
  updateShortListPrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/shortlist-settings",data);
  }
  updateProfilePrivacy(data: any): Observable<any> {
    return this.http.post(baseUrl+"/privacy/update/profile-privacy",data);
  }

  // ************************ update emails alerts *********************************

  getAllEmailSettingDetails(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/alert/get/email-sms/"+member_id);
  }

  updateAllEmailSettingsDetails(data: any): Observable<any> {
    return this.http.post(baseUrl+"/alert/update/email-sms",data);
  }


  // **************************get all count********************************

  getAllCountMaches(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/request/count/accept-request/" + member_id + "/");
  }

  
  // getAllCount(member_id: any): Observable<any> {
  //   return this.http.get(baseUrl + "/request/count/accept-request/" + member_id + "/");
  // }

  
}
