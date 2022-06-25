import { Component, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { UpdateMember } from '../../models/update-member.model';
import { SearchService } from '../../services/search.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-member',
  templateUrl: './update-member.component.html',
  styleUrls: ['./update-member.component.css']
})
export class UpdateMemberComponent implements OnInit {

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.callCountryName();
    this.getReligionName();
    // alert("memberid-"+this.member_id);
    this.setMemberDetails(this.member_id);
  }


  // ***************** for editor start *******************************
  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '15rem',
    minHeight: '5rem',
    maxHeight: 'auto',
    width: 'auto',
    minWidth: '0',
    translate: 'yes',
    enableToolbar: true,
    showToolbar: true,
    placeholder: 'Enter text here...',
    defaultParagraphSeparator: '',
    defaultFontName: '',
    defaultFontSize: '',
    fonts: [
      { class: 'arial', name: 'Arial' },
      { class: 'times-new-roman', name: 'Times New Roman' },
      { class: 'calibri', name: 'Calibri' },
      { class: 'comic-sans-ms', name: 'Comic Sans MS' }
    ],
    toolbarHiddenButtons: [
      ['bold', 'italic'],
      ['fontSize']
    ]
  };
  // ***************** for editor end *******************************

  countryName: any;
  stateName: any;
  cityName: any;
  religionName: any;
  casteName: any;

  showMemberDetails: any;

  isGothraAndAstroShow: boolean = false;
  isShowCastAndSubCast: boolean = true;
  isFatherEmployedRetiredShow: boolean = false;
  isMotherEmployedRetiredShow: boolean = false;
  isFatherBusiness: boolean = false;
  isMotherBusiness: boolean = false;
  isPrivateCompany: boolean = true;

  member_id: any;
  // ***************************  model start ********************
  // member table- profile_created,gender,
  // member details- no of siblings,country
  // new table- astro details,Nakshatra,

  updateMemberModels: UpdateMember = {
    membernative: '',
    height: '',
    weight: '',
    lifestyles: '',
    known_languages: '',
    education: '',
    job: '',
    income: '',
    hobbies: '',
    expectations: '',
    health_info: '',
    blood_group: '',
    gothra: '',
    ethnic_corigin: '',
    pincode: '',
    about_ourself: '',
    mother_tounge: '',
    marital_status: '',
    no_of_children: '',
    date_of_birth: '',
    age: '',
    religion_id: 0,
    cast_id: 0,
    sub_caste_name: '',
    state_id: '',
    city_id: '',
    father_status: '',
    father_company_name: '',
    father_designation: '',
    father_business_name: '',
    mother_status: '',
    mother_company_name: '',
    mother_designation: '',
    mother_business_name: '',
    family_location: '',
    native_place: '',
    family_type: '',
    family_values: '',
    family_affluence: '',
    highest_qualification: '',
    college_attended: '',
    working_with: '',
    working_as: '',
    employer_name: '',
    annual_income: '',
    religion_name: '',
    caste_name: '',
    state_name: '',
    city_name: '',
    profilecreatedby: '',
    gender: '',
    country_Id: '',
    married_male: 0,
    unmarried_male: 0,
    married_female: 0,
    unmarried_female: 0,
    country_name: '',
    manglik: '',
    nakshatra: ''
  };
  // ***************************  model end ********************

  updateMemberDetails() {

    // validation on select dropdown start
    if(this.updateMemberModels.father_status!='Employed'){
      this.updateMemberModels.father_company_name="";
      this.updateMemberModels.father_designation="";
    }
    if(this.updateMemberModels.father_status!='Business'){
      this.updateMemberModels.father_business_name="";
    }

    if(this.updateMemberModels.mother_status!='Employed'){
      this.updateMemberModels.mother_company_name="";
      this.updateMemberModels.mother_designation="";
    }
    if(this.updateMemberModels.father_status!='Business'){
      this.updateMemberModels.mother_business_name="";
    }

    // alert(this.updateMemberModels.religion_name);
    if((this.updateMemberModels.religion_name!='Hindu') && (this.updateMemberModels.religion_name!='Jain')){
      this.updateMemberModels.gothra="";
      // alert("IN");
    }
   
// validation on select dropdown end

    const data = {
      membernative: this.updateMemberModels.membernative,
      height: this.updateMemberModels.height,
      weight: this.updateMemberModels.weight,
      lifestyles: this.updateMemberModels.lifestyles,
      known_languages: this.updateMemberModels.known_languages,
      education: this.updateMemberModels.education,
      job: this.updateMemberModels.job,
      income: this.updateMemberModels.income,
      hobbies: this.updateMemberModels.hobbies,
      expectations: this.updateMemberModels.expectations,
      health_info: this.updateMemberModels.health_info,
      blood_group: this.updateMemberModels.blood_group,
      gothra: this.updateMemberModels.gothra,
      ethnic_corigin: this.updateMemberModels.ethnic_corigin,
      pincode: this.updateMemberModels.pincode,
      about_ourself: this.updateMemberModels.about_ourself,
      mother_tounge: this.updateMemberModels.mother_tounge,
      marital_status: this.updateMemberModels.marital_status,
      no_of_children: this.updateMemberModels.no_of_children,
      date_of_birth: this.updateMemberModels.date_of_birth,
      age: this.updateMemberModels.age,
      religion_id: this.updateMemberModels.religion_id,
      cast_id: this.updateMemberModels.cast_id,
      sub_caste_name: this.updateMemberModels.sub_caste_name,
      state_Id: this.updateMemberModels.state_id,
      city_Id: this.updateMemberModels.city_id,
      father_status: this.updateMemberModels.father_status,
      father_company_name: this.updateMemberModels.father_company_name,
      father_designation: this.updateMemberModels.father_designation,
      father_business_name: this.updateMemberModels.father_business_name,
      mother_status: this.updateMemberModels.mother_status,
      mother_company_name: this.updateMemberModels.mother_company_name,
      mother_designation: this.updateMemberModels.mother_designation,
      mother_business_name: this.updateMemberModels.mother_business_name,
      family_location: this.updateMemberModels.family_location,
      native_place: this.updateMemberModels.native_place,
      family_type: this.updateMemberModels.family_type,
      family_values: this.updateMemberModels.family_values,
      family_affluence: this.updateMemberModels.family_affluence,
      highest_qualification: this.updateMemberModels.highest_qualification,
      college_attended: this.updateMemberModels.college_attended,
      working_with: this.updateMemberModels.working_with,
      working_as: this.updateMemberModels.working_as,
      employer_name: this.updateMemberModels.employer_name,
      annual_income: this.updateMemberModels.annual_income,
      religion_name: this.updateMemberModels.religion_name,
      caste_name: this.updateMemberModels.caste_name,
      state_name: this.updateMemberModels.state_name,
      city_name: this.updateMemberModels.city_name,
      profilecreatedby: this.updateMemberModels.profilecreatedby,
      gender: this.updateMemberModels.gender,
      country_Id: this.updateMemberModels.country_Id,
      married_male: this.updateMemberModels.married_male,
      unmarried_male: this.updateMemberModels.unmarried_male,
      married_female: this.updateMemberModels.married_female,
      unmarried_female: this.updateMemberModels.unmarried_female,
      country_name: this.updateMemberModels.country_name,
      manglik: this.updateMemberModels.manglik,
      nakshatra: this.updateMemberModels.nakshatra
    };
    // alert(" membernative:" + this.updateMemberModels.membernative +
    //  " height:" + this.updateMemberModels.height +
    //  " weight:" + this.updateMemberModels.weight+
    //   "lifestyles:" + this.updateMemberModels.lifestyles+
    //  " known_languages:" + this.updateMemberModels.known_languages+
    //   "education:" + this.updateMemberModels.education+
    //   "job:" + this.updateMemberModels.job+
    //  " income:" + this.updateMemberModels.income+
    //  " hobbies:" + this.updateMemberModels.hobbies+
    //   "expectations:" + this.updateMemberModels.expectations+
    //   "health_info:" + this.updateMemberModels.health_info+
    //   "blood_group:" + this.updateMemberModels.blood_group+
    //  " gothra: "+this.updateMemberModels.gothra+
    //   "ethnic_corigin: "+this.updateMemberModels.ethnic_corigin+
    //  " pincode:" + + this.updateMemberModels.pincode+
    //   "about_ourself: "+this.updateMemberModels.about_ourself+
    //   "mother_tounge:" + this.updateMemberModels.mother_tounge+
    //  " marital_status:" + this.updateMemberModels.marital_status+
    //   "no_of_children:" + this.updateMemberModels.no_of_children+
    //   "date_of_birth:"  + this.updateMemberModels.date_of_birth+
    //   "age: "+this.updateMemberModels.age+
    //  " religion_id:" +  this.updateMemberModels.religion_id+
    //  " cast_id:" + this.updateMemberModels.cast_id+
    //  " sub_caste_name:" + this.updateMemberModels.sub_caste_name+
    //  " state_Id: "+this.updateMemberModels.state_id+
    //  " city_Id:" + this.updateMemberModels.city_id+
    //   "father_status:" + this.updateMemberModels.father_status+
    //   "father_company_name: "+this.updateMemberModels.father_company_name+
    //   "father_designation:" + this.updateMemberModels.father_designation+
    //   "father_business_name:" + this.updateMemberModels.father_business_name+
    //   "mother_status:" + this.updateMemberModels.mother_status+
    //   "mother_company_name:" + this.updateMemberModels.mother_company_name+
    //  " mother_designation: "+this.updateMemberModels.mother_designation+
    //   "mother_business_name:" + this.updateMemberModels.mother_business_name+
    //   "family_location:" + this.updateMemberModels.family_location+
    //   "native_place:" + this.updateMemberModels.native_place+
    //   "family_type:" + this.updateMemberModels.family_type+
    //   "family_values: "+this.updateMemberModels.family_values+
    //  " family_affluence:" + this.updateMemberModels.family_affluence+
    //  " highest_qualification:" + this.updateMemberModels.highest_qualification+
    //   "college_attended:" + this.updateMemberModels.college_attended+
    //   "working_with: "+this.updateMemberModels.working_with+
    //   "working_as:" + this.updateMemberModels.working_as+
    //   "employer_name:" + this.updateMemberModels.employer_name+
    //  " annual_income:" + this.updateMemberModels.annual_income+
    //   "religion_name: "+this.updateMemberModels.religion_name+
    //   "caste_name:" + this.updateMemberModels.caste_name+
    //   "state_name:" + this.updateMemberModels.state_name+
    //  " city_name:" + this.updateMemberModels.city_name+
    //   "profilecreatedby:" + this.updateMemberModels.profilecreatedby+
    //   "gender:" + this.updateMemberModels.gender+
    //   "country_Id:" + this.updateMemberModels.country_Id+
    //   "married_male:" + this.updateMemberModels.married_male+
    //   "unmarried_male: "+this.updateMemberModels.unmarried_male+
    //  " married_female:" + this.updateMemberModels.married_female+
    //  " country_name:" + this.updateMemberModels.country_name+
    //   "unmarried_female:" + this.updateMemberModels.unmarried_female);

    this.searchService.updateMemberDetails(data, this.member_id)
      .subscribe(
        results => {
          this.updateMemberModels = results.message;
          this.setMemberDetails(this.member_id);
          alert("Member Updated....")
        },
        error => {
          console.log(error);
        });
    this.setMemberDetails(this.member_id);
  }


  annualIncomeHideShow(event: any) {
    if (event.target.value == 'Not Working') {
      this.isPrivateCompany = false;
    } else {
      this.isPrivateCompany = true;
    }
  }

  fatherStatusShowHide(event: any) {
    if (event.target.value == 'Employed' || event.target.value == 'Retired') {
      this.isFatherEmployedRetiredShow = true;
    } else {
      this.isFatherEmployedRetiredShow = false;
    }

    if (event.target.value == 'Business') {
      this.isFatherBusiness = true;
    } else {
      this.isFatherBusiness = false;
    }
  }

  motherStatusShowHide(event: any) {
    if (event.target.value == 'Employed' || event.target.value == 'Retired') {
      this.isMotherEmployedRetiredShow = true;
    } else {
      this.isMotherEmployedRetiredShow = false;
    }

    if (event.target.value == 'Business') {
      this.isMotherBusiness = true;
    } else {
      this.isMotherBusiness = false;
    }
  }


  gothraShowAndHide(event: any) {

    const religion_Name = event.target.value;
    this.searchService.getCastNameBYReligionName(religion_Name)
      .subscribe(
        results => {
          this.casteName = results.cast;
          // alert(this.stateName);
        },
        error => {
          console.log(error);
        });

    // 1- Hindu, 6-Jain
    if (event.target.value == 'Hindu' || event.target.value == 'Jain') {
      this.isGothraAndAstroShow = true;
    } else {
      this.isGothraAndAstroShow = false;
    }
    // 5- Parsi , 7- Buddhist, 8-Jewish, 9-No Religion, 10-Spiritual - not religious, 11-Other
    if (event.target.value == 'Parsi' || event.target.value == 'Buddhist' || event.target.value == 'Jewish' || event.target.value == 'No Religion' || event.target.value == 'Spiritual' || event.target.value == 'Other') {
      this.isShowCastAndSubCast = false;
    } else {
      this.isShowCastAndSubCast = true;
    }
  }

  callCountryName() {
    this.searchService.getCountryName()
      .subscribe(
        results => {
          // alert(JSON.stringify(results));
          this.countryName = results.country;

          // alert(this.countryName);
        },
        error => {
          console.log(error);
        });
  }


  getReligionName() {
    this.searchService.getReligionName()
      .subscribe(
        results => {
          this.religionName = results.religion;
          // alert( this.religionName);
          // alert(this.countryName);
        },
        error => {
          console.log(error);
        });
  }

  public onCountrySelected(event: any): void {
    const countryName = event.target.value;
    // alert(countryID);
    this.searchService.getStateNameByCountryName(countryName)
      .subscribe(
        results => {
          this.stateName = results.states;
          // alert(this.stateName);
        },
        error => {
          console.log(error);
        });
  }

  public onStateSelected(event: any): void {
    const state_Name = event.target.value;
    this.searchService.getCityNameByStateName(state_Name)
      .subscribe(
        results => {
          this.cityName = results.cities;
          // alert(this.stateName);
        },
        error => {
          console.log(error);
        });
  }

  setMemberDetails(member_id: any) {
    this.searchService.getMemberDetails(this.member_id)
      .subscribe(
        results => {
          this.updateMemberModels = results.data;
          // alert(JSON.stringify(this.updateMemberModels));
          // alert(JSON.stringify(this.updateMemberModels));
        //  alert(this.updateMemberModels.manglik);

          // set dropdown value
          this.updateMemberModels.country_name = results.data.country_name;

          // get state list by country name  
          this.searchService.getStateNameByCountryID(results.data.country_id)
            .subscribe(
              results => {
                this.stateName = results.state;
              },
              error => {
                console.log(error);
              });
          this.updateMemberModels.state_name = results.data.state;

          // get city list by state name  
          this.searchService.getCityNameByStateID(results.data.state_id)
            .subscribe(
              results => {
                this.cityName = results.cities;
              },
              error => {
                console.log(error);
              });
          this.updateMemberModels.city_name = results.data.city;

          // get cast list by religion name  
          this.searchService.getCastNameBYCountryID(results.data.religion_id)
            .subscribe(
              results => {
                this.casteName = results.cast;
              },
              error => {
                console.log(error);
              });
          this.updateMemberModels.caste_name = results.data.caste;

          // father show content
          if (results.father_status == 'Employed') {
            this.isFatherEmployedRetiredShow = true;
          } else {
            this.isFatherEmployedRetiredShow = false;
          }
          if (results.father_status == 'Business') {
            this.isFatherBusiness = true;
          } else {
            this.isFatherBusiness = false;
          }

          // mother show content
          if (results.mother_status == 'Employed') {
            this.isMotherEmployedRetiredShow = true;
          } else {
            this.isMotherEmployedRetiredShow = false;
          }
          if (results.mother_status == 'Business') {
            this.isMotherBusiness = true;
          } else {
            this.isMotherBusiness = false;
          }

          //  show gothra details if religion is hindu or jain content
          // alert('religion name- '+results.religion_name);
          if (results.religion_name == 'Hindu' || results.religion_name == 'Jain') {
            this.isGothraAndAstroShow = true;
          }else{
            this.isGothraAndAstroShow = false;
          }

        },
        error => {
          console.log(error);
        });
  }


}
