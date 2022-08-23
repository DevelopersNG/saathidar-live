import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersearchDashboardComponent } from './membersearch-dashboard.component';

describe('MembersearchDashboardComponent', () => {
  let component: MembersearchDashboardComponent;
  let fixture: ComponentFixture<MembersearchDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MembersearchDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MembersearchDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
