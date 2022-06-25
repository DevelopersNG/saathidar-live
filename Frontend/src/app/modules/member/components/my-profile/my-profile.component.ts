import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../services/search.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  constructor(private searchService: SearchService,private router:Router) { }


  ngOnInit(): void {
    this.member_id = localStorage.getItem('login_credentials');
    this.setMemberDetails(this.member_id);
  }
  showAlbum(){
    this.router.navigate(['members/album']);
  }


  viewMemberModels:any;

  setMemberDetails(member_id: any) {
   this.searchService.getMemberDetails(this.member_id)
      .subscribe(
        results => {
          this.viewMemberModels = results.data;
          // alert(JSON.stringify(this.viewMemberModels))
          // alert(this.viewMemberModels.gothra);
        },
        error => {
          console.log(error);
        });
  }

  member_id: any;
 

}
