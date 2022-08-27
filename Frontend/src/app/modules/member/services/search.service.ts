import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// const baseUrl = 'http://localhost:8080/api';
// const baseUrl = 'http://192.168.1.37:8080/api';
// const baseUrl ="http://103.150.186.33:8080/saathidaar_backend/api"
const baseUrl = 'http://103.150.186.33:8080/saathidaar_backend/api'
// const baseUrl ="http://69.49.247.33:8080/saathidaar_backend"

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getMemberDetailsByMemberID(memberID: any, login_member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/get-details/" + memberID + "/" + login_member_id);
  }

  getMyProfile(memberID: any): Observable<any> {
    return this.http.get(baseUrl + "/member/my-profile/" + memberID );
  }


  getCountryName(): Observable<any> {
    return this.http.get(baseUrl + "/get/country");
  }

  getStateNameByCountryID(CountryID: any): Observable<any> {
    return this.http.get(baseUrl + "/get/state-name/" + CountryID);
  }

  getCityNameByStateID(StateID: any): Observable<any> {
    return this.http.get(baseUrl + "/get/city-name/" + StateID);
  }

  // ********************** religion - cast *****************************

  getReligionName(): Observable<any> {
    return this.http.get(baseUrl + "/get/religion-name");
  }

  getCastNameBYCountryID(religionID: any): Observable<any> {
    return this.http.get(baseUrl + "/get/cast-name/" + religionID);
  }


  // ******************* update member details *********************
  updateMemberDetails(data: any, memberID: any): Observable<any> {
    return this.http.post(baseUrl + "/member/update/" + memberID, data);
  }

  getMemberDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/get-details/" + member_id);
  }

  // **************************** by name ********************************************

  getCastNameBYReligionName(religion_name: string): Observable<any> {
    return this.http.get(baseUrl + "/get/cast-name/by/religion_name/" + religion_name);
  }

  getCityNameByStateName(state_name: string): Observable<any> {
    // alert(baseUrl+"/get/city-name/by/state-name/"+state_name);
    return this.http.get(baseUrl + "/get/city-name/by/state-name/" + state_name);
  }

  getStateNameByCountryName(country_name: string): Observable<any> {
    // alert(baseUrl+"/get/city-name/by/state-name/"+state_name);
    return this.http.get(baseUrl + "/get/state-name/by/country-name/" + country_name);
  }

  //****************************** update horoscope details *********************************

  updateHoroscopeDetails(data: any, memberID: any): Observable<any> {
    return this.http.post(baseUrl + "/member/horoscope/update/" + memberID, data);
  }

  getHoroscopeDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/horoscope/get/" + member_id);
  }

  // ************************************  update partner-preference ***********************

  updatePartnerPreference(data: any, memberID: any): Observable<any> {
    return this.http.post(baseUrl + "/member/preference/update/" + memberID, data);
  }

  getMemberPreferenceDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/preference/get/" + member_id);
  }


  // ************************************ Search member details ***********************

  updateSearchMembersDetails(data: any, memberID: any): Observable<any> {
    return this.http.post(baseUrl + "/member/search/update/" + memberID, data);
  }

  getSearchMembersDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/search/get/" + member_id);
  }


  // *******************************  get all cast-state-city name ************************


  getAllStateName(): Observable<any> {
    return this.http.get(baseUrl + "/get/state");
  }

  getAllCityName(): Observable<any> {
    return this.http.get(baseUrl + "/get/all/city");
  }

  getAllCastName(): Observable<any> {
    return this.http.get(baseUrl + "/get/all/cast");
  }

  // ********************** search by profile id *********************

  checkProfileIDIsAvailable(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/get-details-by-member-id" + member_id);
  }

  // ******************** set partner preference *************************

  getPartnerPreference(member_id: any, login_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/match/preference/" + member_id + "/" + login_id);
  }



  // **********************************getAllCount*********************

  getAllCount(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/request/count/accept-request/" + member_id + "/");
  }
  searchNewMatchesAllMember(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/new/matches/" + member_id);
  }
 


  searchMyMatchesAllMember(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/my/matches/" + member_id);
  }
  searchTodayMatchesAllMember(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/member/today/matches/" + member_id);
  }
  searchMemberDetails(data: any, memberID: any): Observable<any> {
    return this.http.post(baseUrl + "/member/get-member-by-filter-data/" + memberID, data);
  }

  // *********************  plan upgrade ***********************

  savePremiumMember(data: any): Observable<any> {
    return this.http.post(baseUrl + "/member/upgrade/plan", data);
  }

  // *********************  premium member ***********************
  callNewPreiumMatchesDetails(member_id: any): Observable<any> {
    return this.http.get(baseUrl + "/new/premium/matches/dashboard/"+member_id);
  }

  // *******************  get plan details ******************************
  getMemberPlansDetails(): Observable<any> {
    return this.http.get(baseUrl+"/member/plans-details");
  }

  // ************************ upload photo *************************************
  uploadImages(data: any): Observable<any> {
    return this.http.post(baseUrl + "/member/upload/photo", data);
  }
  
  // uploadImages(data: any): Observable<any> {
  //   return this.http.post(baseUrl + "/member/uploads/photo",data);
  // }

  getData(member_id:any): Observable<any> {
    return this.http.get(baseUrl+"/member/get/photo/"+member_id);
  }

  // deleteImage(member_id:any): Observable<any> {
  //   return this.http.post(baseUrl+"/member/delete/photo/"+member_id);
  // }

  deleteImage(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/delete/photo",data);
  }
   // ********************** URL Image **********************************************44

 getDataImageDataByURL(member_id: any): Observable<any> {
  return this.http.get(baseUrl+"/member/app/get/photo/"+member_id);
 }
  // *******************************set profile***************************
  setProfileImage(image_id:any,member_id:any,data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/profile/photo/"+member_id+"/"+image_id,data);
  }

  uploadKycDetails(data:any): Observable<any> {
    return this.http.post(baseUrl + "/member/upload/kyc/photo",data);
  }

  callKycDoucument(member_id: any): Observable<any> {
    return this.http.get(baseUrl+"/member/app/get/kyc/photo/"+member_id);
   }
   sentRequests(data: any): Observable<any> {
    return this.http.post(baseUrl+"/member/send-request",data);
    
  }

} 