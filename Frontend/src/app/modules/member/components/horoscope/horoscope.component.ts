import { Component, OnInit } from '@angular/core';
import { Horoscope } from '../../models/horoscope';
import { SearchService } from '../../services/search.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-horoscope',
  templateUrl: './horoscope.component.html',
  styleUrls: ['./horoscope.component.css']
})
export class HoroscopeComponent implements OnInit {

  constructor(private searchService: SearchService,
    private router:Router) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.callCountryName();
    this.getHoroscopeDetails(this.member_id);
  }

  erroeMessageProfileId=false;
  member_id:any;
  countryName:any;

  horoscopeDetails:Horoscope={
    country_name: '',
    country_of_birth: '',
    city_of_birth: '',
    hours: '',
    minutes: '',
    time: '',
    time_status: '',
    manglik: '',
    time_of_birth: '',
    message: '',
    profile_id:''
  };
 // call member details by profile id
 getMemberDetailsByProfileID(){
  //  alert(this.horoscopeDetails.profile_id);
  var val=this.horoscopeDetails.profile_id;
  if(val.indexOf("MSD") !== -1 || val.indexOf("FSD") !== -1){
    var getID = val.substring(3,6); 

    this.searchService.checkProfileIDIsAvailable(val)
    .subscribe(
      results => {
        // alert(JSON.stringify(results))
        if(results.message=='success'){
          this.router.navigate(['members/profile/'+getID]);
        }else{
          this.erroeMessageProfileId=true;
        }
      },
      error => {
        console.log(error);
      });
  }else{
    this.erroeMessageProfileId=true;
  }
 
}
  callCountryName() {
    this.searchService.getCountryName()
      .subscribe(
        results => {
          // this.countryName = JSON.stringify(results);
          this.countryName = results.country;
          // alert(this.countryName);
        },
        error => {
          console.log(error);
        });
  }

  updateHoroscopeDetails(){
    const timeOfBirth=this.horoscopeDetails.hours+":"+this.horoscopeDetails.minutes+" "+this.horoscopeDetails.time;
    const data={
      country_of_birth:this.horoscopeDetails.country_of_birth,
      city_of_birth:this.horoscopeDetails.city_of_birth,
      hours:this.horoscopeDetails.hours,
      minutes:this.horoscopeDetails.minutes,
      time:this.horoscopeDetails.time,
      time_status:this.horoscopeDetails.time_status,
      manglik:this.horoscopeDetails.manglik,
      time_of_birth:timeOfBirth
    } 
    // alert(JSON.stringify(data));
    this.searchService.updateHoroscopeDetails(data,this.member_id)
      .subscribe(
        results => {
          this.horoscopeDetails = results;
          // alert(JSON.stringify(results))
          
          this.getHoroscopeDetails(this.member_id);
          // alert("horoscope updated...")
          this.router.navigate(['members/my-profile']);

        },
        error => {
          console.log(error);
        });
  }


  getHoroscopeDetails(member_id: any) {
    this.searchService.getHoroscopeDetails(this.member_id)
    .subscribe(
      results => {
        this.horoscopeDetails =results;
        // alert(JSON.stringify(results))
        this.horoscopeDetails.country_of_birth=results.country_of_birth;
        // alert(this.horoscopeDetails.country_of_birth);
      },
      error => {
        console.log(error);
      });
  }




}
