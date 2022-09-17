import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { SearchService } from 'src/app/modules/member/services/search.service';

@Component({
  selector: 'app-member-details-show',
  templateUrl: './member-details-show.component.html',
  styleUrls: ['./member-details-show.component.css']
})
export class MemberDetailsShowComponent implements OnInit {

  url = "";
  member_id:any;
  viewMemberDetails:any;
  user_id:any;
  imageURL='http://103.174.102.195:8080'
  demoUrls:any;
  data:any;

  constructor(private AdminServiceService:AdminServiceService,private SearchService:SearchService) { }

  ngOnInit(): void {
    this.member_id=localStorage.getItem('m_id');
    this.setMemberDetails(this.member_id);
    this.callIMageForMemberID(this.member_id)
  }

  viewMemberModels:any;
  setMemberDetails(member_id: any) {
   this.SearchService.getMyProfile(this.member_id)
      .subscribe(
        results => {
          this.viewMemberModels = results.data;
        },
        error => {
          console.log(error);
        });
  }

     // *********************************new line code image******************************
     
  deleteImageAdmin(image_id:any) {
    const data={
      photo_id:"20"
    }
    image_id=20;
    this.AdminServiceService.deleteImageAdmin(data,image_id)
    .subscribe(
      results => {
      },
      error => {
        // this.loader = false;
        console.log(error);
      });
  }
//  ****************************set deleteImageAdmin********************
     // *********************************new line code image******************************
     ApprovalImageAdmin(image_id:any) {
      const data={
        // id:image_id,
        // Image_status:"Approval"
        photo_id:"20"
      }
      image_id=20;
      this.AdminServiceService.ApprovalImageAdmin(data,image_id)
      .subscribe(
        results => {
        
        },
        error => {
          // this.loader = false;
          console.log(error);
        });
    }
  
//  ****************************set deleteImageAdmin********************
profileIMags:any;
callIMageForMemberID(member_id:any){
  this.AdminServiceService.getDataImageDataByURL(this.member_id)
  .subscribe(
results => {
    this.demoUrls=results.data;
  alert(JSON.stringify(this.demoUrls))
    
   });
}

}
