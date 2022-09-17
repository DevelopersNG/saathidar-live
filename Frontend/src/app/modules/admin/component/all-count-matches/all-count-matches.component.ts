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
  data:any;

  ngOnInit(): void {
    this.getMemberData();
    localStorage.setItem('m_id', "");
  }

  ShowDetailsMember:ShowDetailsMember = {
    primium_member: '',
    non_primium_member: '',
    gender: '',
    search_date_from: '',
    search_date_to: ''
  }

  alldetails()
  {
    this.router.navigate(['/admin/member-details']);
  }

  singleViewMemberDetails(member_id: string) 
  {
    localStorage.setItem('m_id', member_id);
    this.router.navigate(['/admin/member-details/' + member_id]);
  }

  allMembertDetails:any
  totalRecords: number = 0
  page: number = 1
  getMemberData() {
    const data = {
      Member: this.ShowDetailsMember.primium_member,
      non_primium_member: this.ShowDetailsMember.non_primium_member,
      gender:this.ShowDetailsMember.gender,
      search_date_from: this.ShowDetailsMember.search_date_from,
      search_date_to: this.ShowDetailsMember.search_date_to,
    }
    this.AdminServiceService.getMemberData(data)
      .subscribe(
        (results: any) => {
          this.allMembertDetails=results.data;
          this.totalRecords = this.allMembertDetails.length;
          if (this.totalRecords < 10) {
            this.page = 1;
          }
        },
        (error: any) => {
          console.log(error);
        });
  }
}
