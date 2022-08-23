import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// const baseUrl = 'http://192.168.1.34:8080/api';
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"

//loval server - vikas pc
// const baseUrl = 'http://103.150.186.33:8080/api';

// live server
// const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http: HttpClient) { }
  setMemberDetailsAdmin(userid: any): Observable<any> {
    return this.http.get(baseUrl+"/get-member-id-by-login/"+userid);
  }
  deleteImageAdmin(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/otp",data);
  }

  ApprovalImageAdmin(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/otp",data);
  }
  getMemberData(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/get-member-id-by-login/"+member_id);
  }
}
