import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../services/search.service';
import {Router} from '@angular/router';
import { UpdateMember } from '../../models/update-member.model';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  constructor(private searchService: SearchService,private router:Router) { }
  member_id: any;
  demoUrls:any;
  imageURL='http://103.174.102.195:8080'
  gender:any;
  genderImageURL='/saathidaar/assets/img'
  Kyc = "";
  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.setMemberDetails(this.member_id);
    this.callIMageForMemberID(this.member_id)
    this.callKycDoucument(this.member_id)
  // start
  this.gender = localStorage.getItem('login_credentials_gender');
  if(this.gender=='male'){
    this.genderImageURL=this.genderImageURL+"/No_image_female.jpg";
  }
  if(this.gender=='female'){
    this.genderImageURL=this.genderImageURL+"/No_image_male.jpg";
  }
  // end
  }
  showAlbum(){
    this.router.navigate(['members/album']);
  }
  viewMemberModels:any;
  setMemberDetails(member_id: any) {
   this.searchService.getMyProfile(this.member_id)
      .subscribe(
        results => {
          this.viewMemberModels = results.data;
        },
        error => {
          console.log(error);
        });
  }
// **************************my profile**********************************
  // ****************************************** STart
  profileIMags:any;
  callIMageForMemberID(member_id:any){
    this.searchService.getDataImageDataByURL(this.member_id)
    .subscribe((results : any) => {
     
      this.profileIMags=results.data[0].member_images;
     });
  }
  // *********************************************** End
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
    nakshatra: '',
    Aadhaar_card: '',
    pan_card: '',
    Kyc_Upload: '',
    ug_education: ''
  };
  // urls=[];
  myForm: any;
  images: any;
  urls : string[] = [];
  namefile:any

  
  onselectFile(event:any) {
   if (event.target.files &&  event.target.files[0].name) {
     var File = event.target.files.length;
     const target = event.target as HTMLInputElement
     for(let i=0; i<File; i++){
       var reader = new FileReader();
       this.namefile =event.target.files[0].name;
       reader.onload=(events:any)=>{
         this.urls.push(events.target.result);
         this.myForm.patchValue({
           fileSource: this.images
        });
       }  
       reader.readAsDataURL(event.target.files[i]);       
     }
   }
 }

 
  saveImages(){

    const data={
      image_base_urls:this.urls,
      member_id:this.member_id,
      Aadhaar_card:this.updateMemberModels.Aadhaar_card,
      document_type:this.updateMemberModels.Aadhaar_card,
      document_name:this.namefile,
      headers: { 'Content-Type': 'multipart/form-data' }
    }

   this.searchService.uploadKycDetails(data)
    .subscribe(
     results => {
      },
      error => {
        console.log(error);
    });
  }


  kycDoumrntMember:any;
  documtName:any;
  documtPath:any;
  documtType:any;

  callKycDoucument(member_id:any){
    this.searchService.callKycDoucument(member_id)
    .subscribe((results : any) => {
     if(results.data!= '' )
     {
      this.kycDoumrntMember=results.data[0];
     }
       });
  }
}