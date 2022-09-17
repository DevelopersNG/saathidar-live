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
  imageURL='http://103.174.102.195:8080'
  constructor(private landingPageServices: LandingPageService,) { }
  ngOnInit(): void {
    this.callIMageForStory(this.data)
    this.callIMageForAdv()
    // this.LandingPageService.deleteAvd(this.images)
 

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


 saveImagesSuccessStoris(){
  const data={
    success_photo:this.adminLoginModel.detailsSuccessStoris,
    image_base_urls:this.urls,
    headers: { 'Content-Type': 'multipart/form-data' }
  }
  // alert(JSON.stringify(data))
 this.landingPageServices.uploadImagesSuccessStoris(data)
  .subscribe(
   results => {
    window.location.reload();
    },
    error => {
      console.log(error);
  });
}
  
  getStoryprofileIMags:any;
  callIMageForStory(data:any){
    this.landingPageServices.getImageSuccessStory()
    .subscribe((results : any) => {
      this.getStoryprofileIMags=results.data;
      // this.demoUrls=results.data;
     });
  }
  deletestory(image_id:any) {
    const data={
      image_id:image_id
      // detailsSuccessStoris:this.adminLoginModel.detailsSuccessStoris
    }
    
    this.landingPageServices.deletestory(data,image_id)
    .subscribe(d => {
      
            window.location.reload();
      // this.callIMageForStory(data);
   }, error => {
     console.log(error);
   });
  }

// **********************************delete story**********************

// *************************************************************************************************************************************
//  avd*********************************************************************
urlss : string[] = [];
onselectFileAd(event:any) {
 if (event.target.files &&  event.target.files[0]) {
   if (event.target.files[0].size < 500000) {
   var File = event.target.files.length;
   for(let i=0; i<File; i++){
     var reader = new FileReader();
     reader.onload=(events:any)=>{
        // alert(events.target.result) 
       this.urlss.push(events.target.result);
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

saveImagesAd(){
  const data={
    // detailsSuccessStoris:this.adminLoginModel.detailsSuccessStoris,
    image_base_urls:this.urlss,
    headers: { 'Content-Type': 'multipart/form-data' }
  }
 this.landingPageServices.uploadImagesAdv(data)
  .subscribe(
   results => {
    window.location.reload();
    // alert(JSON.stringify(results))
    },
    error => {
      console.log(error);
  });
}
// ****************************************** STart
getAdvprofileIMags:any;
callIMageForAdv(){
  this.landingPageServices.getImageAvd()
  .subscribe((results : any) => {
    // alert(JSON.stringify(results))
    this.getAdvprofileIMags=results.data;
    // this.demoUrls=results.data;
   });
}
// *********************************************** End
// **********************************delete story**********************
deleteAdv(image_id:any) {
  const data={
    image_id:image_id
    // detailsSuccessStoris:this.adminLoginModel.detailsSuccessStoris
  }
  this.landingPageServices.deleteAvd(data,image_id)
  .subscribe(d => {
    window.location.reload();

    // this.callIMageForStory(data);
 }, error => {
   console.log(error);
 });
}
}
