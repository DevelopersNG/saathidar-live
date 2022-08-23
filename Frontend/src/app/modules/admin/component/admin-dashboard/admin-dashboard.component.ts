import { Component, OnInit } from '@angular/core';
import { AdminLoginModel } from 'src/app/models/login-model.model';
import { LandingPageService } from 'src/app/services/landing-page.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  url = "";
  myForm: any;
  images: any;
  data:any;
  UploadPhotoThree:boolean =true;
  constructor(private landingPageServices: LandingPageService,) { }
  ngOnInit(): void {
    this.callIMageForMemberID(this.data)

  }
  adminLoginModel: AdminLoginModel = {
    usernameAdmin:'',
    passwordAdmin:'',
    detailsSuccessStoris:'',
  };
  urls : string[] = [];
  onselectFile(event:any) {
   if (event.target.files &&  event.target.files[0]) {
     if (event.target.files[0].size < 500000) {
     var File = event.target.files.length;
     for(let i=0; i<File; i++){
       var reader = new FileReader();
       reader.onload=(events:any)=>{
          // alert(events.target.result) 
         this.urls.push(events.target.result);
         this.myForm.patchValue({
           fileSource: this.images
        });
       }  
       reader.readAsDataURL(event.target.files[i]);       
     }
   }else
   {
     alert('Each photo must be less than 50KB')
   }
   }
 }

 saveImages(){
  const data={
    detailsSuccessStoris:this.adminLoginModel.detailsSuccessStoris,
    image_base_urls:this.urls,
    // headers: { 'Content-Type': 'multipart/form-data' }
  }
  alert(JSON.stringify(data))
  
 this.landingPageServices.uploadImagesAdmin(data)
  .subscribe(
   results => {
    },
    error => {
      console.log(error);
  });
}
// **************************my profile**********************************
  // ****************************************** STart
  profileIMags:any;
  callIMageForMemberID(data:any){
    this.landingPageServices.getImageSuccessStory(data)
    .subscribe((results : any) => {
     
      this.profileIMags=results.data[0].member_images;
      // this.demoUrls=results.data;
     });
  }
  // *********************************************** End

// **********************************delete story**********************
deleteStory(image_id:any,) {
  const data={
    id:image_id
    // detailsSuccessStoris:this.adminLoginModel.detailsSuccessStoris
  }
  alert(data)
  this.landingPageServices.deleteStory(data).subscribe(d => {
    this.callIMageForMemberID(data);
 }, error => {
   console.log(error);
 });
}
}
