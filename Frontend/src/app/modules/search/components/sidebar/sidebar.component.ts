import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { NgxNavigationWithDataComponent } from 'ngx-navigation-with-data';
import { FilterMatches } from '../../models/filter-matches';
import { MatchesService } from '../../services/matches.service';
import { SharingService } from '../../services/sharing.service';
import * as uuid from 'uuid';

declare var $: any;
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private matchesServices: MatchesService,
    private router: Router,
    public navCtrl: NgxNavigationWithDataComponent,
    private sharingService: SharingService) { }

  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    // alert(this.member_id);
    this.getReligionList();
    this.getAllCountryList();
    this.getAllStateList();
  }

  // set data from db

  member_id: any;
  // **************************** FILTER WORK START *******************************
  // ***************************** religion  *******************************
  valueReligions: string;
  selectedValuesReligion = [] as any;

  constName: String;
  selectCheckBox_Religion(evt: any, value: string) {
   
    this.constName = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesReligion.push(evt.target.value);
    } else {
      this.selectedValuesReligion = this.selectedValuesReligion.filter((v: any) => v !== evt.target.value)
    }
    this.valueReligions = this.selectedValuesReligion.join(',')
    console.log("religion details- " + this.valueReligions);
    this.getMemberDetailsByReligionData(value+"/"+this.constName+"/" + uuid.v4());
  }

  // ***************************** state *******************************
  valueState: string;
  selectedValuesState = [] as any;

  selectCheckBox_State(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;

    this.constName = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesState.push(evt.target.value);
    } else {
      this.selectedValuesState = this.selectedValuesState.filter((v: any) => v !== evt.target.value)
    }
    this.valueState = this.selectedValuesState.join(',')
    console.log("state details- " + this.valueState);

    this.getMemberDetailsByReligionData(value+"/"+this.constName+"/" + uuid.v4());
  }

  // ***************************** matches all *******************************

  valueAll: string;
  selectedValuesAll = [] as any;

  selectCheckBox_MatchesAll(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesAll.push(evt.target.value);
    } else {
      this.selectedValuesAll = this.selectedValuesAll.filter((v: any) => v !== evt.target.value)
    }
    this.valueAll = this.selectedValuesAll.join(',')
    console.log("All Matches details- " + this.valueAll);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }

  // **************************** Recently Joined *******************************


  valueRecentlyJoined: string;
  selectedValuesRecentlyJoined = [] as any;

  selectCheckBox_RecentlyJoined(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    //  var pos = this.selectedValuesRecentlyJoined.indexOf(value);

    //  if(pos==0){
    this.selectedValuesRecentlyJoined.splice(this.selectedValuesRecentlyJoined - 1, 1);
    //  }
    //  if(pos!=0){

    if (status) {
      this.selectedValuesRecentlyJoined.push(evt.target.value);
    } else {
      this.selectedValuesRecentlyJoined = this.selectedValuesRecentlyJoined.filter((v: any) => v !== evt.target.value)
    }
    // }

    this.valueRecentlyJoined = this.selectedValuesRecentlyJoined.join(',')
    console.log("Recently Joined Details- " + this.valueRecentlyJoined);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }



  // ***************************** Annual Income  *******************************

  valueAnnualIncome: string;
  selectedValuesAnnualIncome = [] as any;

  selectCheckBox_AnnualIncome(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesAnnualIncome.push(evt.target.value);
    } else {
      this.selectedValuesAnnualIncome = this.selectedValuesAnnualIncome.filter((v: any) => v !== evt.target.value)
    }
    this.valueAnnualIncome = this.selectedValuesAnnualIncome.join(',')
    console.log("Anuual Income details- " + this.valueAnnualIncome);
    // alert(this.member_id);
    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }

  // ***************************** Marital Status  *******************************

  valueMaritalStatus: string;
  selectedValuesMaritalStatus = [] as any;

  selectCheckBox_MaritalStatus(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesMaritalStatus.push(evt.target.value);
    } else {
      this.selectedValuesMaritalStatus = this.selectedValuesMaritalStatus.filter((v: any) => v !== evt.target.value)
    }
    this.valueMaritalStatus = this.selectedValuesMaritalStatus.join(',')
    console.log("Marital Status details- " + this.valueMaritalStatus);
    // alert(this.member_id);
    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }


  // ***************************** Country All *******************************

  valueCountry: string;
  selectedValuesCountry = [] as any;

  selectCheckBox_Country(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    this.constName = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesCountry.push(evt.target.value);
    } else {
      this.selectedValuesCountry = this.selectedValuesCountry.filter((v: any) => v !== evt.target.value)
    }
    this.valueCountry = this.selectedValuesCountry.join(',')
    console.log("Country details- " + this.valueCountry);

    this.getMemberDetailsByReligionData(value+"/"+this.constName+"/" + uuid.v4());
  }



  // ***************************** Working With *******************************

  valueWorkingWith: string;
  selectedValuesWorkingWith = [] as any;

  selectCheckBox_WorkingWith(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesWorkingWith.push(evt.target.value);
    } else {
      this.selectedValuesWorkingWith = this.selectedValuesWorkingWith.filter((v: any) => v !== evt.target.value)
    }
    this.valueWorkingWith = this.selectedValuesWorkingWith.join(',')
    console.log("working with details- " + this.valueWorkingWith);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }



  // ***************************** Profile Creatde by *******************************

  valueProfileCreatedBy: string;
  selectedValuesProfileCreatedBy = [] as any;

  selectCheckBox_ProfileCreatedBy(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesProfileCreatedBy.push(evt.target.value);
    } else {
      this.selectedValuesProfileCreatedBy = this.selectedValuesProfileCreatedBy.filter((v: any) => v !== evt.target.value)
    }
    this.valueProfileCreatedBy = this.selectedValuesProfileCreatedBy.join(',')
    console.log("profile created by details- " + this.valueProfileCreatedBy);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }



  // ***************************** Life Styles Diet *******************************

  valueLifeStyles: string;
  selectedValuesLifeStyles = [] as any;

  selectCheckBox_LifeStyles(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesLifeStyles.push(evt.target.value);
    } else {
      this.selectedValuesLifeStyles = this.selectedValuesLifeStyles.filter((v: any) => v !== evt.target.value)
    }
    this.valueLifeStyles = this.selectedValuesLifeStyles.join(',')
    console.log("life styles details- " + this.valueLifeStyles);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }


  // ***************************** Life Styles Diet *******************************

  valueEducationalLevels: string;
  selectedValuesEducationslLevel = [] as any;

  selectCheckBox_EducationalLevel(evt: any, value: string) {
    // value: String;
    // value = evt.target.value;
    const status = evt.target.checked;
    if (status) {
      this.selectedValuesEducationslLevel.push(evt.target.value);
    } else {
      this.selectedValuesEducationslLevel = this.selectedValuesEducationslLevel.filter((v: any) => v !== evt.target.value)
    }
    this.valueEducationalLevels = this.selectedValuesEducationslLevel.join(',')
    console.log("Educational Levels details- " + this.valueEducationalLevels);

    this.getMemberDetailsByReligionData(value+"/"+ uuid.v4());
  }

  // **************************** FILTER WORK END *******************************

  filterData: FilterMatches = {
    religion_list: '',
    state_list: ''
  }

  addToCart: any;
  selectedValuesForFilter = [] as any;
  data: any;



  // for set all filter data  
  religionName: any;
  stateList: any;
  countryList: any;
  all: any;
  recentlyJoined: any;
  annualIncome: any;

  previousValue: string;

  getMemberDetailsByReligionData(value: string) {

    this.data = {
      religion_list: this.valueReligions,
      state_list: this.valueState,
      all: this.valueAll,
      recentlyJoined: this.valueRecentlyJoined,
      annualIncome: this.valueAnnualIncome,
      maritalStatus: this.valueMaritalStatus,
      country: this.valueCountry,
      workingWith: this.valueWorkingWith,
      profileCreadtedBy: this.valueProfileCreatedBy,
      lifeStyles: this.valueLifeStyles,
      educationalLevels: this.valueEducationalLevels
    }
    // this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    // this.router.onSameUrlNavigation = 'reload';
    this.sharingService.sharingValue = JSON.stringify(this.data);
    // localStorage.setItem("filter_data",JSON.stringify(data));

    // if (this.previousValue == value) {
    //   value = "all";
    // }
    // this.previousValue = value;
    this.router.navigate(['/matches/refine-search/' + value]);
  }

  // get all religion list
  getAllStateList() {
    this.matchesServices.getStateList()
      .subscribe(
        results => {
          this.stateList = results.state;
        },
        error => {
          console.log(error);
        });
  }

  // get all state list getReligionList
  getReligionList() {
    this.matchesServices.getReligionName()
      .subscribe(
        results => {
          this.religionName = results.religion;
        },
        error => {
          console.log(error);
        });
  }

  // get all country list
  getAllCountryList() {
    this.matchesServices.getCountryList()
      .subscribe(
        results => {
          this.countryList = results.country;
        },
        error => {
          console.log(error);
        });
  }
}
