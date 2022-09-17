import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://103.174.102.195:8080/saathidaar_backend/api'
// const baseUrl = 'http://192.168.1.38:9094/api';
// const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"

@Injectable({
  providedIn: 'root'
})


export class SearchService {
  constructor(private http: HttpClient) { }
  // *******************  get plan details ******************************

  getMemberPlansDetails(): Observable<any> {
    return this.http.get(baseUrl+"/member/plans-details");
  }
  savePremiumMember(data: any): Observable<any> {
    return this.http.post(baseUrl + "/member/upgrade/plan", data);
  }
  // *********************  premium member ******************************
  callNewPreiumMatchesDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/new/premium/matches/dashboard/"+member_id);
  }

  // *******************  get plan details ******************************
} 