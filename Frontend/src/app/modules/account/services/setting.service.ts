import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const baseUrl = 'http://localhost:9094/api';
// const baseUrl = 'http://192.168.1.37:9094/api';

@Injectable({
  providedIn: 'root'
})
export class SettingService {

  constructor(private http: HttpClient) { }

  changePassword(data: any): Observable<any> {
    return this.http.post(baseUrl+"/users/changepwd",data);
  }
  saveProfile(member_id:any,data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/hide/"+member_id,data);
  }

}
