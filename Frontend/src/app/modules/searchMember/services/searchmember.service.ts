import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://192.168.1.35:8080/api';
// const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"
// const baseUrl = 'http://103.174.102.195:8080/saathidaar_backend/api'

@Injectable({
  providedIn: 'root'
})
export class SearchmemberService {

  constructor(private http: HttpClient) { }
  getMemberDetailsByMemberID(memberID: any): Observable<any> {
    return this.http.get(baseUrl+"/member/get-details/"+memberID);
  }
  getCountryName(): Observable<any> {
    return this.http.get(baseUrl+"/get/country");
  }
  getStateNameByCountryID(CountryID: any): Observable<any> {
    return this.http.get(baseUrl+"/get/state-name/"+CountryID);
  }
  getCityNameByStateID(StateID: any): Observable<any> {
    return this.http.get(baseUrl+"/get/city-name/"+StateID);
  }
  // ********************** religion - cast *****************************
  getReligionName(): Observable<any> {
    return this.http.get(baseUrl+"/get/religion-name");
  }
  getCastNameBYCountryID(religionID: any): Observable<any> {
    return this.http.get(baseUrl+"/get/cast-name/"+religionID);
  }
  // ******************* update member details *********************
  updateMemberDetails(data:any ,memberID: any): Observable<any> {
    return this.http.post(baseUrl+"/member/update/"+memberID,data);
  }
  getMemberDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/get-details/"+member_id);
  }
// **************************** by name ********************************************
getCastNameBYReligionName(religion_name: string): Observable<any> {
  return this.http.get(baseUrl+"/get/cast-name/by/religion_name/"+religion_name);
}
getCityNameByStateName(state_name: string): Observable<any> {
  // alert(baseUrl+"/get/city-name/by/state-name/"+state_name);
  return this.http.get(baseUrl+"/get/city-name/by/state-name/"+state_name);
}
getStateNameByCountryName(country_name: string): Observable<any> {
  // alert(baseUrl+"/get/city-name/by/state-name/"+state_name);
  return this.http.get(baseUrl+"/get/state-name/by/country-name/"+country_name);
}
// ****************************** update horoscope details *********************************
updateHoroscopeDetails(data:any ,memberID: any): Observable<any> {
  return this.http.post(baseUrl+"/member/horoscope/update/"+memberID,data);
}
getHoroscopeDetails(member_id: any): Observable<any> {
  return this.http.get(baseUrl+"/member/horoscope/get/"+member_id);
}
// ************************************  update partner-preference ***********************
updatePartnerPreference(data:any ,memberID: any): Observable<any> {
  return this.http.post(baseUrl+"/member/preference/update/"+memberID,data);
}
getMemberPreferenceDetails(member_id: any): Observable<any> {
  return this.http.get(baseUrl+"/member/preference/get/"+member_id);
}
// ************************************ Search member details ***********************
updateSearchMembersDetails(data:any ,memberID: any): Observable<any> {
  return this.http.post(baseUrl+"/member/search/update/"+memberID,data);
}
getSearchMembersDetails(member_id: any): Observable<any> {
  return this.http.get(baseUrl+"/member/search/get/"+member_id);
}
// *******************************  get all cast-state-city name ************************
getAllStateName(): Observable<any> {
  return this.http.get(baseUrl+"/get/state");
}
getAllCityName(): Observable<any> {
  return this.http.get(baseUrl+"/get/all/city");
}
getAllCastName(): Observable<any> {
  return this.http.get(baseUrl+"/get/all/cast");
}
// ********************** search by profile id *********************
checkProfileIDIsAvailable(member_id: any): Observable<any> {
  return this.http.get(baseUrl+"/check/profile/id/available/"+member_id);
}
// ******************** set partner preference *************************
getPartnerPreference(member_id: any,login_id:any): Observable<any> {
  return this.http.get(baseUrl+"/member/match/preference/"+member_id+"/"+login_id);
}
}
