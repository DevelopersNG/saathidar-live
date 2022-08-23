import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.css']
})
export class MemberDetailsComponent implements OnInit {
  member_id:any;
  viewMemberDetails:any;
  constructor(private AdminServiceService:AdminServiceService,) { }

  ngOnInit(): void {
  }
  setMemberDetailsAdmin(member_id: any) {
    this.AdminServiceService.setMemberDetailsAdmin(this.member_id)
       .subscribe(
         results => {
           this.viewMemberDetails = results.data;
           // alert(JSON.stringify(this.viewMemberModels))
           // alert(this.viewMemberModels.gothra);
         },
         error => {
           console.log(error);
         });
   }


     // *********************************new line code image******************************
  
  deleteImageAdmin(image_id:any) {
    const data={
      id:image_id,
      Image_status:"Reject"
    }
    this.AdminServiceService.deleteImageAdmin(data)
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
        id:image_id,
        Image_status:"Approval"
      }
      this.AdminServiceService.ApprovalImageAdmin(data)
      .subscribe(
        results => {
    
        
        },
        error => {
          // this.loader = false;
          console.log(error);
        });
    }
  
  //  ****************************set deleteImageAdmin********************
}
