import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const baseUrl = 'http://localhost:9094/api';
// const baseUrl = 'http://192.168.1.37:9094/api';

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  constructor(private http: HttpClient) { }

  getReligionName(): Observable<any> {
    return this.http.get(baseUrl+"/get/religion-name");
  }

  getCountryList(): Observable<any> { 
    return this.http.get(baseUrl+"/get/country");
  }

  getStateList(): Observable<any> { 
    return this.http.get(baseUrl+"/get/state");
  }

// get member data by filter data
  getMemberDataByFilters(data:any,member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/get-all-member/"+member_id,data);
}

searchMemberDetails(data: any,memberID: any): Observable<any> {
  return this.http.post(baseUrl+"/member/get-member-by-filter-data/"+memberID,data);
}

// ********************* requests services *************************

sentRequests(data: any): Observable<any> {
  return this.http.post(baseUrl+"/member/send-request",data);
}


// ********************* short lists services *************************

getShortListMember(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/shortlist/get/all/"+member_id);
}

removeShortListData(data: any): Observable<any> {
  return this.http.post(baseUrl+"/member/remove-to-shortlist",data);
}

// ******************** Matches **************************************


searchNewMatchesAllMember(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/new/matches/"+member_id);
}
searchMyMatchesAllMember(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/my/matches/"+member_id);
}
searchTodayMatchesAllMember(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/today/matches/"+member_id);
}

// ********************** shortlist add *********************************
sentShortListData(data: any): Observable<any> {
  return this.http.post(baseUrl+"/member/add-to-shortlist",data);
}

// ******************************view**********************

recentVisitor(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/recent-visitors/"+member_id);
}
viewRecently(member_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/view-to/"+member_id);
}




}
