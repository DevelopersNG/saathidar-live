import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// const baseUrl = 'http://192.168.1.37:9094/api';
const baseUrl = 'http://localhost:9094/api';

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

  acceptMemberRequests(data:any): Observable<any> {
    return this.http.post(baseUrl+"/member/request-accept-reject",data);
  }

  getdeletedMemberRequests(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/request/rejected/get/all/"+member_id);
  }
  
  getAcceptedDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/request/accepted/get/all/"+member_id);
  }
}



