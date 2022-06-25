import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { FormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { NgSelectModule } from "@ng-select/ng-select";
import { SearchService } from '../../services/search.service';
import { PartnerPreferenceModel } from '../../models/partner-preference-model';
import * as $ from 'jquery';

@Component({
  selector: 'app-partner-preference',
  templateUrl: './partner-preference.component.html',
  styleUrls: ['./partner-preference.component.css']
})
export class PartnerPreferenceComponent implements OnInit {


  constructor(private searchService: SearchService) { }


  dropdownList: any;
  selectedItems: any;
  dropdownSettings: IDropdownSettings;

  dropdownListMotherTongue: any; 
  selectedItemsMotherTongue: any;
  dropdownSettingsMotherTongue: IDropdownSettings;

  dropdownListQualification: any;
  selectedItemsQualification: any;
  dropdownSettingsQualification: IDropdownSettings;

  dropdownListWorkingWith: any;
  selectedItemsWorkingWith: any;
  dropdownSettingsWorkingWith: IDropdownSettings;

  dropdownListProfessionArea: any;
  selectedItemsProfessionArea: any;
  dropdownSettingsProfessionArea: IDropdownSettings;

  dropdownListCountry: any;
  selectedItemsCountry: any;
  dropdownSettingsCountry: IDropdownSettings;

  dropdownListState: any;
  selectedItemsState: any;
  dropdownSettingsState: IDropdownSettings;

  dropdownListCity: any;
  selectedItemsCity: any;
  dropdownSettingsCity: IDropdownSettings;

  dropdownListReligions: any;
  selectedItemsReligions: any;   
  dropdownSettingsReligions: IDropdownSettings;

  dropdownListCast: any;     
  selectedItemsCast: any;
  dropdownSettingsCast: IDropdownSettings;

  item_id: number;
  item_text: string;
  member_id: any;

  get_marital_status: any;
  get_mother_tongue: any;
  get_qualification: any;
  get_working_with: any;
  get_professional_area: any;
  get_religions: any;
  get_cast: any;
  get_country: any;
  get_state: any;
  get_city: any;
  i: number;

  partner_preference_model: PartnerPreferenceModel = {
    partner_from_age: '',
    partner_to_age: '',
    partner_from_height: '',
    partner_to_height: '',
    partner_manglik_all: '',
    partner_annual_income: '',
    partner_profile_created: '',
    partner_lifestyles: '',
    message:''
  };


  updatePartnerPreference() {

    $(".overlay").show();
    // marital status
    if (this.selectedItems != null) {
      for (var i = 0; i < this.selectedItems.length; i++) {
        if (i == 0) {
          this.get_marital_status = this.selectedItems[i].item_text;
        } else {
          this.get_marital_status = this.get_marital_status + "," + this.selectedItems[i].item_text;
        }
      }
    }

    // Mother tongue
    if (this.selectedItemsMotherTongue != null) {
      for (var i = 0; i < this.selectedItemsMotherTongue.length; i++) {
        if (i == 0) {
          this.get_mother_tongue = this.selectedItemsMotherTongue[i].item_text;
        } else {
          this.get_mother_tongue = this.get_mother_tongue + "," + this.selectedItemsMotherTongue[i].item_text;
        }
      }
    }
   
    // qualification
    if (this.selectedItemsQualification != null) {
      for (var i = 0; i < this.selectedItemsQualification.length; i++) {
        if (i == 0) {
          this.get_qualification = this.selectedItemsQualification[i].item_text;
        } else {
          this.get_qualification = this.get_qualification + "," + this.selectedItemsQualification[i].item_text;
        }
      }
    }

    // working_with
    if (this.selectedItemsWorkingWith != null) {
      for (var i = 0; i < this.selectedItemsWorkingWith.length; i++) {
        if (i == 0) {
          this.get_working_with = this.selectedItemsWorkingWith[i].item_text;
        } else {
          this.get_working_with = this.get_working_with + "," + this.selectedItemsWorkingWith[i].item_text;
        }
      }
    }

    // professional_area
    if (this.selectedItemsProfessionArea != null) {
      for (var i = 0; i < this.selectedItemsProfessionArea.length; i++) {
        if (i == 0) {
          this.get_professional_area = this.selectedItemsProfessionArea[i].item_text;
        } else {
          this.get_professional_area = this.get_professional_area + "," + this.selectedItemsProfessionArea[i].item_text;
        }
      }
    }

    // religions
    if (this.selectedItemsReligions != null) {
      for (var i = 0; i < this.selectedItemsReligions.length; i++) {
        if (i == 0) {
          this.get_religions = this.selectedItemsReligions[i].religion_name;
        } else {
          this.get_religions = this.get_religions + "," + this.selectedItemsReligions[i].religion_name;
        }
      }
    }

  
    // cast
      // alert("get cast by quama"+  JSON.stringify(this.selectedItemsCast));
    if (this.selectedItemsCast != null) {
      for (var i = 0; i < this.selectedItemsCast.length; i++) {
        if (i == 0) {
          this.get_cast = this.selectedItemsCast[i].cast_name;
        } else {
          this.get_cast = this.get_cast + "," + this.selectedItemsCast[i].cast_name;
        }
      }
    }

    // country
    if (this.selectedItemsCountry != null) {
      for (var i = 0; i < this.selectedItemsCountry.length; i++) {
        if (i == 0) {
          this.get_country = this.selectedItemsCountry[i].country_name;
        } else {
          this.get_country = this.get_country + "," + this.selectedItemsCountry[i].country_name;
        }
      }
    }

    // state
    if (this.selectedItemsState != null) {
      for (var i = 0; i < this.selectedItemsState.length; i++) {
        if (i == 0) {
          this.get_state = this.selectedItemsState[i].state_name;
        } else {
          this.get_state = this.get_state + "," + this.selectedItemsState[i].state_name;
        }
      }
    }

    // city
    if (this.selectedItemsCity != null) {
      for (var i = 0; i < this.selectedItemsCity.length; i++) {
        if (i == 0) {
          this.get_city = this.selectedItemsCity[i].city_name;
        } else {
          this.get_city = this.get_city + "," + this.selectedItemsCity[i].city_name;
        }
      }
    }

    const data = {
      partner_marital_status: this.get_marital_status,
      partner_mother_tongue: this.get_mother_tongue,
      partner_qualification: this.get_qualification,
      partner_working_with: this.get_working_with,
      partner_professional_area: this.get_professional_area,
      partner_religions: this.get_religions,
      partner_cast: this.get_cast,
      partner_country: this.get_country,
      partner_state: this.get_state,
      partner_city: this.get_city,
      partner_from_age: this.partner_preference_model.partner_from_age,
      partner_to_age: this.partner_preference_model.partner_to_age,
      partner_from_height: this.partner_preference_model.partner_from_height,
      partner_to_height: this.partner_preference_model.partner_to_height,
      partner_manglik_all: this.partner_preference_model.partner_manglik_all,
      partner_annual_income: this.partner_preference_model.partner_annual_income,
      partner_profile_created: this.partner_preference_model.partner_profile_created,
      partner_lifestyles: this.partner_preference_model.partner_lifestyles
    }
    // alert(JSON.stringify(data));

    // save the data
   
    this.searchService.updatePartnerPreference(data,this.member_id)
      .subscribe(
        results => {
          $(".overlay").hide();
          // this.dropdownListCity = results;
          alert(results.message);
        },
        error => {
          console.log(error);
        });
  }
  // dropdownList;
  // IDropdownSettings;
  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    $(".overlay").show();
    // ***************************** marital status *****************************
    this.dropdownList = [
      { item_id: 1, item_text: 'Married' },
      { item_id: 2, item_text: 'Divorce' },
      { item_id: 3, item_text: 'single' },
      { item_id: 4, item_text: 'widowed' },
      { item_id: 5, item_text: 'awaiting divorce' },
      { item_id: 6, item_text: 'Unmarried' }
    ];
 
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };


    // ***************************** mother tongue *****************************

    this.dropdownListMotherTongue = [
      { item_id: 1, item_text: 'Hindi' },
      { item_id: 2, item_text: 'Marathi' },
      { item_item_id: 3, item_text: 'Punjabi' },
      { item_id: 4, item_text: 'Bengali' },
      { item_id: 5, item_text: 'Gujarati' },
      { item_id: 6, item_text: 'Urdu' },
      { item_id: 7, item_text: 'Telugu' },
      { item_id: 8, item_text: 'Kannada' },
      { item_id: 9, item_text: 'English' },
      { item_id: 10, item_text: 'Tamil' },
      { item_id: 11, item_text: 'Odia' },
      { item_id: 12, item_text: 'Marwari' },
      { item_id: 13, item_text: 'Arunachali' },
      { item_id: 14, item_text: 'Assamese' },
      { item_id: 15, item_text: 'Awadhi' },
      { item_id: 16, item_text: 'Bhojpuri' },
      { item_id: 17, item_text: 'Chattisgarhi' },
      { item_id: 18, item_text: 'Haryanavi' },
      { item_id: 19, item_text: 'Himachali/Pahari' },
      { item_id: 20, item_text: 'Kashmiri' },
      { item_id: 21, item_text: 'Khandesi' },
      { item_id: 22, item_text: 'Malayalam' },
      { item_id: 23, item_text: 'Manipuri' },
      { item_id: 21, item_text: 'Rajasthani' },
      { item_id: 22, item_text: 'Sanskrit' },
      { item_id: 23, item_text: 'Sindhi' },
      { item_id: 23, item_text: 'Other' }
    ];

    this.dropdownSettingsMotherTongue = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };

    // ***************************** Qualifications *****************************
    this.dropdownListQualification = [
      { item_id: 1, item_text: 'B.E / B.Tech' },
      { item_id: 2, item_text: 'M.E / M.Tech' },
      { item_id: 3, item_text: 'M.S Engineering' },
      { item_id: 4, item_text: 'B.Eng (Hons)' },
      { item_id: 5, item_text: 'M.Eng (Hons)' },
      { item_id: 6, item_text: 'Engineering Diploma' },
      { item_id: 7, item_text: 'AE' },
      { item_id: 8, item_text: 'AET' },
      { item_id: 9, item_text: 'High school' },
      { item_id: 10, item_text: 'B.A' },

      { item_id: 11, item_text: 'B.Ed' },
      { item_id: 12, item_text: 'BJMC' },
      { item_id: 13, item_text: 'BFA' },
      { item_id: 14, item_text: 'B.Arch' },
      { item_id: 15, item_text: 'B.Des' },
      { item_id: 16, item_text: 'BMM' },
      { item_id: 17, item_text: 'MFA' },
      { item_id: 18, item_text: 'M.Ed' },
      { item_id: 19, item_text: 'M.A' },
      { item_id: 20, item_text: 'MSW' },

      { item_id: 21, item_text: 'MJMC' },
      { item_id: 22, item_text: 'M.Arch' },
      { item_id: 23, item_text: 'M.Des' },
      { item_id: 24, item_text: 'BA (Hons)' },
      { item_id: 25, item_text: 'B.Arch (Hons)' },
      { item_id: 26, item_text: 'DFA' },
      { item_id: 27, item_text: 'D.Ed' },
      { item_id: 28, item_text: 'D.Arch' },
      { item_id: 29, item_text: 'AA' },
      { item_id: 30, item_text: 'AFA' },
      { item_id: 31, item_text: 'Less than high school' },
      { item_id: 32, item_text: 'B.Com' },
      { item_id: 33, item_text: 'CA / CPA' },
      { item_id: 34, item_text: 'CFA' },
      { item_id: 35, item_text: 'CS' },
      { item_id: 36, item_text: 'BSc / BFin' },
      { item_id: 37, item_text: 'M.Com' },
      { item_id: 38, item_text: 'MSc / MFin / MS' },
      { item_id: 39, item_text: 'BCom (Hons)' },
      { item_id: 40, item_text: 'PGD Finance' },

      { item_id: 41, item_text: 'BCA' },
      { item_id: 42, item_text: 'B.IT' },
      { item_id: 43, item_text: 'BCS' },
      { item_id: 44, item_text: 'BA Computer Science' },
      { item_id: 45, item_text: 'MCA' },
      { item_id: 46, item_text: 'PGDCA' },
      { item_id: 47, item_text: 'IT Diploma' },
      { item_id: 48, item_text: 'ADIT' },
      { item_id: 49, item_text: 'B.Sc' },
      { item_id: 50, item_text: 'M.Sc' },

      { item_id: 51, item_text: 'BSc (Hons)' },
      { item_id: 52, item_text: 'MBBS' },
      { item_id: 53, item_text: 'BDS' },
      { item_id: 54, item_text: 'BPT' },
      { item_id: 55, item_text: 'BAMS' },
      { item_id: 56, item_text: 'BHMS' },
      { item_id: 57, item_text: 'B.Pharma' },
      { item_id: 58, item_text: 'BVSc' },
      { item_id: 59, item_text: 'BSN / BScN' },
      { item_id: 60, item_text: 'MDS' },

      { item_id: 61, item_text: 'MCh' },
      { item_id: 62, item_text: 'M.D' },
      { item_id: 63, item_text: 'M.S Medicine' },
      { item_id: 64, item_text: 'MPT' },
      { item_id: 65, item_text: 'DM' },
      { item_id: 66, item_text: 'M.Pharma' },
      { item_id: 67, item_text: 'MVSc' },
      { item_id: 68, item_text: 'MMed' },
      { item_id: 69, item_text: 'PGD Medicine' },
      { item_id: 70, item_text: 'BBA' },

      { item_id: 71, item_text: 'BHM' },
      { item_id: 72, item_text: 'BBM' },
      { item_id: 73, item_text: 'MBA' },
      { item_id: 74, item_text: 'PGDM' },
      { item_id: 75, item_text: 'ABA' },
      { item_id: 76, item_text: 'ADBus' },
      { item_id: 77, item_text: 'BL / LLB' },
      { item_id: 78, item_text: 'ML / LLM' },
      { item_id: 79, item_text: 'LLB (Hons)' },
      { item_id: 80, item_text: 'ALA' },

      { item_id: 81, item_text: 'Ph.D' },
      { item_id: 82, item_text: 'M.Phil' },
      { item_id: 83, item_text: 'Bachelor' },
      { item_id: 84, item_text: 'Master' },
      { item_id: 85, item_text: 'Diploma' },
      { item_id: 86, item_text: 'Honours' },
      { item_id: 87, item_text: 'Doctorate' },
      { item_id: 88, item_text: 'Associate' }

    ];
   
    this.dropdownSettingsQualification = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };


    // ***************************** Working With *****************************

    this.dropdownListWorkingWith = [
      { item_id: 1, item_text: 'Private Company' },
      { item_id: 2, item_text: 'Government / Public Sector' },
      { item_id: 3, item_text: 'Defense / Civil Services' },
      { item_id: 4, item_text: 'Business / Self Employed' },
      { item_id: 5, item_text: 'Not Working' }
    ];
  
    this.dropdownSettingsWorkingWith = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };

    // ***************************** Professional Area *****************************
    this.dropdownListProfessionArea = [
      { item_id: 1, item_text: 'Banking Professional' },
      { item_id: 2, item_text: 'Chartered Accountant' },
      { item_id: 3, item_text: 'Company Secretary' },
      { item_id: 4, item_text: 'Finance Professional' },
      { item_id: 5, item_text: 'Investment Professional' },
      { item_id: 6, item_text: 'Accounting Professional (Others)' },
      { item_id: 7, item_text: 'Admin Professional' },
      { item_id: 8, item_text: 'Actor' },
      { item_id: 9, item_text: 'Advertising Professional' },
      { item_id: 10, item_text: 'Entertainment Professional)' },

      { item_id: 11, item_text: 'Event Manager' },
      { item_id: 12, item_text: 'Journalist' },
      { item_id: 13, item_text: 'Media Professional' },
      { item_id: 14, item_text: 'Public Relations Professional' },
      { item_id: 15, item_text: 'Farming' },
      { item_id: 16, item_text: 'Horticulturist' },
      { item_id: 17, item_text: 'Agricultural Professional (Others)' },
      { item_id: 18, item_text: 'Air Hostess / Flight Attendant' },
      { item_id: 19, item_text: 'Pilot / Co-Pilot' },
      { item_id: 20, item_text: 'Other Airline Professional' },

      { item_id: 21, item_text: 'Architect' },
      { item_id: 22, item_text: 'Interior Designer' },
      { item_id: 23, item_text: 'Landscape Architect' },
      { item_id: 24, item_text: 'Animator' },
      { item_id: 25, item_text: 'Commercial Artist' },
      { item_id: 26, item_text: 'Web / UX Designers' },
      { item_id: 27, item_text: 'Artist (Others)' },
      { item_id: 28, item_text: 'Beautician' },
      { item_id: 29, item_text: 'Fashion Designer' },
      { item_id: 30, item_text: 'Hairstylist' },

      { item_id: 31, item_text: 'Jewellery Designer' },
      { item_id: 32, item_text: 'Designer (Others)' },
      { item_id: 33, item_text: 'Customer Support / BPO / KPO Professional' },
      { item_id: 34, item_text: 'IAS / IRS / IES / IFS' },
      { item_id: 35, item_text: 'Indian Police Services (IPS)' },
      { item_id: 36, item_text: 'Law Enforcement Employee (Others)' },
      { item_id: 37, item_text: 'Airforce' },
      { item_id: 38, item_text: 'Army' },
      { item_id: 39, item_text: 'Navy' },
      { item_id: 40, item_text: 'Defense Services (Others)' },

      { item_id: 41, item_text: 'Lecturer' },
      { item_id: 42, item_text: 'Professor' },
      { item_id: 43, item_text: 'Research Assistant' },
      { item_id: 44, item_text: 'Research Scholar' },
      { item_id: 45, item_text: 'Teacher' },
      { item_id: 46, item_text: 'Training Professional (Others)' },
      { item_id: 47, item_text: 'Civil Engineer' },
      { item_id: 48, item_text: 'Electronics / Telecom Engineer' },
      { item_id: 49, item_text: 'Mechanical / Production Engineer' },
      { item_id: 50, item_text: 'Non IT Engineer (Others)' },

      { item_id: 51, item_text: 'Chef / Sommelier / Food Critic' },
      { item_id: 52, item_text: 'Catering Professional' },
      { item_id: 53, item_text: 'Hotel' },
      { item_id: 54, item_text: 'Software Developer / Programmer' },
      { item_id: 55, item_text: 'Software Consultant' },
      { item_id: 56, item_text: 'Hardware' },
      { item_id: 57, item_text: 'Software Professional (Others)' },
      { item_id: 58, item_text: 'Lawyer' },
      { item_id: 59, item_text: 'Legal Assistant' },
      { item_id: 60, item_text: 'Legal Professional (Others)' },

      { item_id: 61, item_text: 'Dentist' },
      { item_id: 62, item_text: 'Doctor' },
      { item_id: 63, item_text: 'Medical Transcriptionist' },
      { item_id: 64, item_text: 'Nurse' },
      { item_id: 65, item_text: 'Pharmacist' },
      { item_id: 66, item_text: 'Physician Assistant' },
      { item_id: 67, item_text: 'Physiotherapist / Occupational Therapist' },
      { item_id: 68, item_text: 'Psychologist' },
      { item_id: 69, item_text: 'Surgeon' },
      { item_id: 70, item_text: 'Veterinary Doctor' },

      { item_id: 71, item_text: 'Therapist (Others)' },
      { item_id: 72, item_text: 'Medical / Healthcare Professional (Others)' },
      { item_id: 73, item_text: 'Merchant Naval Officer' },
      { item_id: 74, item_text: 'Mariner' },
      { item_id: 75, item_text: 'Marketing Professional' },
      { item_id: 76, item_text: 'Sales Professional' },
      { item_id: 77, item_text: 'Biologist / Botanist' },
      { item_id: 78, item_text: 'Physicist' },
      { item_id: 79, item_text: 'Science Professional (Others)' },
      { item_id: 80, item_text: 'CxO / Chairman / Director / President' },

      { item_id: 81, item_text: 'VP / AVP / GM / DGM' },
      { item_id: 82, item_text: 'Sr. Manager / Manager' },
      { item_id: 83, item_text: 'Consultant / Supervisor / Team Leads' },
      { item_id: 84, item_text: 'Team Member / Staff' },
      { item_id: 85, item_text: 'Agent / Broker / Trader / Contractor' },
      { item_id: 86, item_text: 'Business Owner / Entrepreneur' },
      { item_id: 87, item_text: 'Politician' },
      { item_id: 88, item_text: 'Social Worker / Volunteer / NGO' },
      { item_id: 89, item_text: 'Sportsman' },
      { item_id: 90, item_text: 'Travel' },

      { item_id: 91, item_text: 'Writer' },
      { item_id: 92, item_text: 'Student' },
      { item_id: 93, item_text: 'Retired' },
      { item_id: 94, item_text: 'Not working' }
    ];
  
    this.dropdownSettingsProfessionArea = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };


    // ***************************** Country *****************************

    this.searchService.getCountryName()
      .subscribe(
        results => {
          this.dropdownListCountry =  results.country;
        },
        error => {
          console.log(error);
        });


    this.dropdownSettingsCountry = {
      singleSelection: false,
      idField: 'country_id',
      textField: 'country_name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };


    // ***************************** get all State*****************************
    
    this.searchService.getAllStateName()
    .subscribe(
      results => {
        this.dropdownListState = results.state;
      },
      error => {
        console.log(error);
      });

    this.dropdownSettingsState = {
      singleSelection: false,
      idField: 'state_id',
      textField: 'state_name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };

    // ***************************** get all city  *****************************
    
    this.searchService.getAllCityName()
    .subscribe(
      results => {
        this.dropdownListCity = results.city;
      },
      error => {
        console.log(error);
      });

    this.dropdownSettingsCity = {
      singleSelection: false,
      idField: 'city_id',
      textField: 'city_name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };

    // ***************************** get all Religions *****************************
    
    this.searchService.getReligionName()
      .subscribe(
        results => {
          this.dropdownListReligions = results.religion;
        },
        error => {
          console.log(error);
        });


    this.dropdownSettingsReligions = {
      singleSelection: false,
      idField: 'religion_id',
      textField: 'religion_name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };


    // ***************************** get all cast *****************************
  
    this.searchService.getAllCastName()
    .subscribe(
      results => {
        this.dropdownListCast = results.cast;
      },
      error => {
        console.log(error);
      });

    this.dropdownSettingsCast = {
      singleSelection: false,
      idField: 'cast_id',
      textField: 'cast_name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      // itemsShowLimit: 3,
      allowSearchFilter: true
    };

    // get updated values 
    this.searchService.getMemberPreferenceDetails(this.member_id)
    .subscribe(
      results => {

    this.partner_preference_model.partner_from_age =results.partner_from_age;
    this.partner_preference_model.partner_to_age =results.partner_to_age;
    this.partner_preference_model.partner_from_height =results.partner_from_height;
    this.partner_preference_model.partner_to_height =results.partner_to_height;
    this.partner_preference_model.partner_manglik_all =results.partner_manglik_all;
    this.partner_preference_model.partner_annual_income =results.partner_annual_income;
    this.partner_preference_model.partner_profile_created =results.partner_profile_created;
    this.partner_preference_model.partner_lifestyles =results.partner_lifestyles;

    //set marital status
    var getValue=results.partner_marital_status;
    this.selectedItems=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["item_text"] =val[i];      
            this.selectedItems.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["item_text"] =getValue;      
          this.selectedItems.push(this.item);
        }
    }

    // set mother tongue
    var getValue=results.partner_mother_tongue;
    this.selectedItemsMotherTongue=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["item_text"] =val[i];      
            this.selectedItemsMotherTongue.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["item_text"] =getValue;      
          this.selectedItemsMotherTongue.push(this.item);
        }
    }

    // set qualifications
    var getValue=results.partner_qualification;
    this.selectedItemsQualification=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["item_text"] =val[i];      
            this.selectedItemsQualification.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["item_text"] =getValue;      
          this.selectedItemsQualification.push(this.item);
        }
    }

    // set working with
    var getValue=results.partner_working_with;
    this.selectedItemsWorkingWith=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["item_text"] =val[i];      
            this.selectedItemsWorkingWith.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["item_text"] =getValue;      
          this.selectedItemsWorkingWith.push(this.item);
        }
    }



    // set professional_area
    var getValue=results.partner_professional_area;
    this.selectedItemsProfessionArea=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["item_text"] =val[i];      
            this.selectedItemsProfessionArea.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["item_text"] =getValue;      
          this.selectedItemsProfessionArea.push(this.item);
        }
    }
    

    // set country 
    var getValue=results.partner_country;
    this.selectedItemsCountry=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){    
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["country_name"] =val[i];      
            this.selectedItemsCountry.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["country_name"] =getValue;      
          this.selectedItemsCountry.push(this.item);
        }
    }



    // set state 

    var getValue=results.partner_state;
    this.selectedItemsState=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["state_name"] =val[i];      
            this.selectedItemsState.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["state_name"] =getValue;      
          this.selectedItemsState.push(this.item);
        }
    }

    // set city 
    var getValue=results.partner_city;
    this.selectedItemsCity=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["city_name"] =val[i];      
            this.selectedItemsCity.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["city_name"] =getValue;      
          this.selectedItemsCity.push(this.item);
        }
    }

    // set partner_religions 
      var getValue=results.partner_religions;
      this.selectedItemsReligions=[];
      if(getValue!=''){
          if(getValue.indexOf(",") !== -1){
            const val=getValue.split(',');
            for(var i=0;i<val.length;i++){
              this.item = {};
              this.item ["religion_name"] =val[i];      
              this.selectedItemsReligions.push(this.item);
            }
          }else{
            this.item = {};
            this.item ["religion_name"] =getValue;      
            this.selectedItemsReligions.push(this.item);
          }
      }

    // set partner_cast_db 
    var getValue=results.partner_cast;
    this.selectedItemsCast=[];
    if(getValue!=''){
        if(getValue.indexOf(",") !== -1){
          const val=getValue.split(',');
          for(var i=0;i<val.length;i++){
            this.item = {};
            this.item ["cast_name"] =val[i];      
            this.selectedItemsCast.push(this.item);
          }
        }else{
          this.item = {};
          this.item ["cast_name"] =getValue;      
          this.selectedItemsCast.push(this.item);
        }
    }
    $(".overlay").hide();
      },
      error => {
        console.log(error);
        $(".overlay").hide();
      });

  }

  item:any;

  //  ************** country *************************8
  onItemSelectCountry(item: any) {
    // this.searchService.getStateNameByCountryID(item.country_id)
    //   .subscribe(
    //     results => {
    //       this.dropdownListState = results;
    //     },
    //     error => {
    //       console.log(error);
    //     });
    // console.log(JSON.stringify(item));
    // alert(item.country_id);
  }
  onSelectAllCountry(items: any) {
    // console.log(items);
  }


  //  ************** state *************************8
  onItemSelectState(item: any) {
    // get city list by state name  
    // this.searchService.getCityNameByStateID(item.state_id)
    //   .subscribe(
    //     results => {
    //       this.dropdownListCity = results;
    //     },
    //     error => {
    //       console.log(error);
    //     });
    // alert(item.state_name);
  }
  onSelectAllState(items: any) {
    console.log(items);
  }

  // *************** City *************
  onItemSelectCity(item: any) {
    console.log(item);
  }
  onSelectAllCity(items: any) {
    console.log(items);
  }


  // *************** religions *************
  onItemSelectReligions(item: any) {
    // this.searchService.getCastNameBYCountryID(item.religion_id)
    //   .subscribe(
    //     results => {
    //       this.dropdownListCast = results;
    //     },
    //     error => {
    //       console.log(error);
    //     });
  }
  onSelectAllReligions(items: any) {
    console.log(items);
  }

  // *************** caste name *************
  onItemSelectCast(item: any) {
    console.log(item);
    // alert(item);
  }
  onSelectAllCast(items: any) {
    console.log(items);
  }

  // *************** All *************
  onItemSelect(item: any) {
    console.log(item);
    // this.getValue=this.getValue+ ","+item.item_text;
    // alert(this.getValue);
  }
  onSelectAll(items: any) {
    console.log(items);
  }
}









