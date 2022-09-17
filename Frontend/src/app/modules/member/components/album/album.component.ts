import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
// import { AnyRecord } from 'dns';
// import * as events from 'events';
import * as jQuery from 'jquery';
import { SearchService } from '../../services/search.service';


@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css']
})
export class AlbumComponent implements OnInit {
  url = "";
  myForm: any;
  images: any;
imagecount:any=0

  constructor(private router:Router,private searchService:SearchService) { }
  UploadPhotoThree:boolean =true;
  member_id:any;
  // demoUrls : string[] = [];
  demoUrls : any;
  name = 'Test display image';
  imageurls:string="";
  // thumbnail: any;
  // imageURL='http://103.150.186.33:8080'
  imageURL='http://103.174.102.195:8080'
  ngOnInit(): void {
// *****************************show image ***************
    this.member_id = localStorage.getItem('login_credentials');

       // *****************************show image ***************
        this.searchService.deleteImage(this.images);
        this.callIMageForMemberID(this.member_id)
  }
   // ****************************************** STart
profileIMags:any;
callIMageForMemberID(member_id:any){
  this.searchService.getDataImageDataByURL(member_id)
  .subscribe((results : any) => { 
    // alert(JSON.stringify(results))
    this.profileIMags=results.data[0].member_images;
    this.demoUrls=results.data;
this.imagecount=results.data.length;
if(this.imagecount==2 )
{
 this.UploadPhotoThree=false;
}
   
      // this.demoUrls.push(results);
    // alert(this.demoUrls)
   });
}
// *********************************************** End
  setImages(val:any){
  // alert(val)
    return val.data[0].member_images;
  }

  showAccount(){
    this.router.navigate(['members/account']);
  }

  // urls=[];
   urls : string[] = [];
   onselectFile(event:any) {
    if (event.target.files &&  event.target.files[0]) {
      // if (event.target.files[0].size < 500000) {
      var File = event.target.files.length;
      if(this.imagecount<=1)
      {
      if(event.target.files.length <= 2){
      for(let i=0; i<File; i++){
        var reader = new FileReader();
        reader.onload=(events:any)=>{
          //  alert(events.target.result) 
          this.urls.push(events.target.result);
          this.myForm.patchValue({
            fileSource: this.images
         });
        }  
        reader.readAsDataURL(event.target.files[i]);       
      }
    }
  }else
  {
    alert('only two photo upload')
  }
    // }else
    // {
    //   alert('Each photo must be less than 50KB')
    // }
    }
  }
  seturl:any;
  showimage(url:any)
{
 this.seturl=url;

}
  saveImages(){
    const data={
      image_base_urls:this.urls,
      member_id:this.member_id,
      headers: { 'Content-Type': 'multipart/form-data' }
    }
    // alert(this.member_id)
   this.searchService.uploadImages(data)
    .subscribe(
     results => {
      //  alert(JSON.stringify(results))
      },
      error => {
        console.log(error);
    });
  }

  // *********************************new line code image******************************
  deleteImage(image_id:any) {
    const data={
      id:image_id
    }
    this.searchService.deleteImage(data).subscribe(d => {
      // alert('Image removed')
      this.callIMageForMemberID(this.member_id);
   }, error => {
     console.log(error);
   });
  }
//  ****************************set profile********************
setprofileMemberImage:any;
image_id:any
setProfileImage(id:any)
{
  const data={
    image_id:id
    
}
// alert(JSON.stringify(data))

  this.searchService.setProfileImage(id,this.member_id,data)
  .subscribe(
    results => {
      // alert(JSON.stringify(results))
  //  this.setprofileMemberImage=results.data;
    },
    error => {
      console.log(error);
    });
}
//  ****************************set profile********************
}

  

