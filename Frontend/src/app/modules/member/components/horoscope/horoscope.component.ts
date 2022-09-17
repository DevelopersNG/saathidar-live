import { Component, OnInit } from '@angular/core';
import { Horoscope, location } from '../../models/horoscope';
import { SearchService } from '../../services/search.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-horoscope',
  templateUrl: './horoscope.component.html',
  styleUrls: ['./horoscope.component.css']
})

export class HoroscopeComponent implements OnInit {
  showLatLng=false
  city:any;

  constructor(private searchService: SearchService,private _blog:SearchService,
    private router:Router) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    if(this.horoscopeDetails.city_of_birth=='')
    {
      this.showLatLng=false
    }
    this.callCountryName();
    this.getHoroscopeDetails(this.member_id);

    this.searchService.getHoroscopeDetails(this.member_id)
      .subscribe(
        results => {
          this.city=results.city_of_birth;
          this.getLocation(this.city);
        },
        error => {
          console.log(error);
        });
    // ++++++++++++++++++++++++++++++++++++++++++++++++
  }
  countryName:any;
  locations:location={
    latt: '',
    longt: ''
  }
  latLocation:any
  langLocatin:any
 
  getLocation(city:any){
  
  this.searchService.getMyLatLong(city)
      .subscribe(
        (results:any) => {
          // alert(JSON.stringify(results))
     
          this.latLocation = results.latt;
          this.langLocatin = results.longt;
        },
        error => {
          console.log(error);
        });
  }

  erroeMessageProfileId=false;
  member_id:any;
 
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
    profile_id: '',
    date_of_birth: '',
 
  };

 // call member details by profile id
 getMemberDetailsByProfileID(){
  var val=this.horoscopeDetails.profile_id;
  if(val.indexOf("MSD") !== -1 || val.indexOf("FSD") !== -1){
    var getID = val.substring(3,6); 

    this.searchService.checkProfileIDIsAvailable(val)
    .subscribe(
      results => {
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
      time_of_birth:timeOfBirth,
      date_of_birth:this.horoscopeDetails.date_of_birth
    } 
    this.searchService.updateHoroscopeDetails(data,this.member_id)
      .subscribe(
        results => {
          this.horoscopeDetails = results;
          this.getHoroscopeDetails(this.member_id);
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
        this.horoscopeDetails.country_of_birth=results.country_of_birth;
        this.city=results.city_of_birth;
      },
      error => {
        console.log(error);
      });
  }


}
