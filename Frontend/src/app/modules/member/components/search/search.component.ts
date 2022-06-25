import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { SearchMember } from '../../models/search-member.model';
import { valHooks } from 'jquery';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  erroeMessageProfileId=false;

  constructor(private searchService: SearchService,private router:Router) { }

    getValue:any;

  dropdownList: any;
  selectedItems: any;
  dropdownSettings: IDropdownSettings;

  dropdownListMotherTongue: any;
  selectedItemsMotherTongue: any;
  dropdownSettingsMotherTongue: IDropdownSettings;

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

  member_id: any;

  get_marital_status: any;
  get_mother_tongue: any;
  get_religions: any;
  get_cast: any;
  get_country: any;
  get_state: any;
  get_city: any;


  search_member: SearchMember = {
    search_from_age: '',
    search_to_age: '',
    search_from_height: '',
    search_to_height: '',
    message: '',
    profile_id:''
  }
  item: any;

  ngOnInit(): void {
    $(".overlay").show();
    this.member_id = localStorage.getItem('login_credentials');
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

    // ***************************** Country *****************************

    this.searchService.getCountryName()
      .subscribe(
        results => {
          this.dropdownListCountry = results;
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

    // ***************************** get all State *****************************

    this.searchService.getAllStateName()
      .subscribe(
        results => {
          this.dropdownListState = results;
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

    // ***************************** get all city *****************************

    this.searchService.getAllCityName()
      .subscribe(
        results => {
          this.dropdownListCity = results;
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
          this.dropdownListReligions = results;
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


    // ***************************** get all cast name *****************************

    this.searchService.getAllCastName()
      .subscribe(
        results => {
          this.dropdownListCast = results;
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


    this.searchService.getSearchMembersDetails(this.member_id)
      .subscribe(
        results => {
          this.search_member.search_from_age = results.search_from_age;
          this.search_member.search_to_age = results.search_to_age;
          this.search_member.search_from_height = results.search_from_height;
          this.search_member.search_to_height = results.search_to_height;

          //set marital status
          if(typeof results.search_marital_status=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_marital_status;
          }
         
          this.selectedItems = [];
          if (this.getValue != '') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["item_text"] = val[i];
                this.selectedItems.push(this.item);
              }
            } else {
              this.item = {};
              this.item["item_text"] = this.getValue;
              this.selectedItems.push(this.item);
            }
          }

          // set mother tongue
          if(typeof results.search_mother_tongue=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_mother_tongue;
          }
          this.selectedItemsMotherTongue = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["item_text"] = val[i];
                this.selectedItemsMotherTongue.push(this.item);
              }
            } else {
              this.item = {};
              this.item["item_text"] = this.getValue;
              this.selectedItemsMotherTongue.push(this.item);
            }
          }



          // set country 
          if(typeof results.search_country=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_country;
          }
          this.selectedItemsCountry = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["country_name"] = val[i];
                this.selectedItemsCountry.push(this.item);
              }
            } else {
              this.item = {};
              this.item["country_name"] = this.getValue;
              this.selectedItemsCountry.push(this.item);
            }
          }



          // set state 
          if(typeof results.search_state=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_state;
          }
          this.selectedItemsState = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["state_name"] = val[i];
                this.selectedItemsState.push(this.item);
              }
            } else {
              this.item = {};
              this.item["state_name"] = this.getValue;
              this.selectedItemsState.push(this.item);
            }
          }

          // set city 
          if(typeof results.search_city=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_city;
          }
          this.selectedItemsCity = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["city_name"] = val[i];
                this.selectedItemsCity.push(this.item);
              }
            } else {
              this.item = {};
              this.item["city_name"] = this.getValue;
              this.selectedItemsCity.push(this.item);
            }
          }

          // set search_religions 
          if(typeof results.search_religions=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_religions;
          }
          this.selectedItemsReligions = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["religion_name"] = val[i];
                this.selectedItemsReligions.push(this.item);
              }
            } else {
              this.item = {};
              this.item["religion_name"] = this.getValue;
              this.selectedItemsReligions.push(this.item);
            }
          }

          // set search_cast_db 
          if(typeof results.search_cast=='undefined')
          {
            this.getValue="";
          }else{
            this.getValue = results.search_cast;
          }
          this.selectedItemsCast = [];
          if (this.getValue != '' && this.getValue!='undefined') {
            if (this.getValue.indexOf(",") !== -1) {
              const val = this.getValue.split(',');
              for (var i = 0; i < val.length; i++) {
                this.item = {};
                this.item["cast_name"] = val[i];
                this.selectedItemsCast.push(this.item);
              }
            } else {
              this.item = {};
              this.item["cast_name"] = this.getValue;
              this.selectedItemsCast.push(this.item);
            }
          }
          $(".overlay").hide();
        },
        error => {
          $(".overlay").hide();
          console.log(error);
        });


  }


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
    console.log(JSON.stringify(item));
    // alert(item.country_id);
  }
  onSelectAllCountry(items: any) {
    console.log(items);
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
    console.log(JSON.stringify(item));
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
    console.log(JSON.stringify(item));
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

  callSearchMembers() {
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
      search_marital_status: this.get_marital_status,
      search_mother_tongue: this.get_mother_tongue,
      search_religions: this.get_religions,
      search_cast: this.get_cast,
      search_country: this.get_country,
      search_state: this.get_state,
      search_city: this.get_city,
      search_from_age: this.search_member.search_from_age,
      search_to_age: this.search_member.search_to_age,
      search_from_height: this.search_member.search_from_height,
      search_to_height: this.search_member.search_to_height,
    }

    // alert(JSON.stringify(data));
    // save the data

    this.searchService.updateSearchMembersDetails(data, this.member_id)
      .subscribe(
        results => {
          // $(".overlay").hide();
          // this.dropdownListCity = results;
          // alert(results.message);
          this.router.navigate(['matches/members']);
        },
        error => {
          console.log(error);
        });
  }


  // call member details by profile id
  getMemberDetailsByProfileID(){
    var val=this.search_member.profile_id;
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
}

