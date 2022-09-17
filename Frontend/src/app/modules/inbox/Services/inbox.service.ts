import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


// const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"
// const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'
const baseUrl = 'http://103.174.102.195:8080/saathidaar_backend/api'

@Injectable({
  providedIn: 'root'
})
export class InboxService {

  constructor(private http: HttpClient) { }

  getSentDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/request/sent/get/all/"+member_id);
  }

  getInvitationsDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/request/invitations/get/all/"+member_id);
  }

  getInvitationsdelete(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/request/invitations/get/all/"+member_id);
  }

  acceptMemberRequests(data:any): Observable<any> {
    return this.http.post(baseUrl+"/member/request-accept-reject",data);
  }

  getdeletedMemberRequests(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/request/rejected/get/all/"+member_id);
  }
  
  getAcceptedDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/request/accepted/get/all/"+member_id);
  }

  getData(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/member/get/photo/"+member_id);
  }
  getAllinboxCount(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/request/count/accept-request/" + member_id + "/");
  }

  sentShortListData(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/add-to-shortlist",data);
  }

  blockmember(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/block-member",data);
  }

  blockhMemberDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/get/block-member/"+member_id);
  }

  // ********************** URL Image **********************************************44

  getDataImageDataByURL(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/app/get/photo/"+member_id);
  }

}



