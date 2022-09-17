import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAlldetailsComponent } from './profile-alldetails.component';

describe('ProfileAlldetailsComponent', () => {
  let component: ProfileAlldetailsComponent;
  let fixture: ComponentFixture<ProfileAlldetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileAlldetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileAlldetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
