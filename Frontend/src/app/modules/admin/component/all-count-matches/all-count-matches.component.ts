import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShowDetailsMember } from '../../admin-premium.model';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-all-count-matches',
  templateUrl: './all-count-matches.component.html',
  styleUrls: ['./all-count-matches.component.css']
})
export class AllCountMatchesComponent implements OnInit {

  constructor( private router: Router,private AdminServiceService:AdminServiceService,) { }

  ngOnInit(): void {
  }

  ShowDetailsMember:ShowDetailsMember = {
    primium_member: '',
    non_primium_member: '',
    gendar: '',
    search_date_from: '',
    search_date_to: ''
  }
  alldetails()
  {
    this.router.navigate(['/admin/member-details']);
  }

  singleViewMemberDetails(member_id: string) {
    this.router.navigate(['/members/profile/' + member_id]);
  }



  getMemberData() {

    const data = {
      Member: this.ShowDetailsMember.primium_member,
      // non_primium_member: this.ShowDetailsMember.non_primium_member,
      gendar: this.ShowDetailsMember.gendar,
      search_date_from: this.ShowDetailsMember.search_date_from,
      search_date_to: this.ShowDetailsMember.search_date_to,
    }
    alert(JSON.stringify(data))
    this.AdminServiceService.getMemberData(data)
      .subscribe(
        (results: any) => {

       

          // alert(this.allStudentDetails)
        },
        (error: any) => {
          console.log(error);
        });
  }

}
