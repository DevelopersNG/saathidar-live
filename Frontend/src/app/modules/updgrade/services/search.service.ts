import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:9094/api';
// const baseUrl = 'http://192.168.1.37:9094/api';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  // *******************  get plan details ******************************
  getMemberPlansDetails(): Observable<any> {
    return this.http.get(baseUrl+"/member/plans-details");
  }

} 