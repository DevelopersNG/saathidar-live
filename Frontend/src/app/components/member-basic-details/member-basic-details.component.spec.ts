import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberBasicDetailsComponent } from './member-basic-details.component';

describe('MemberBasicDetailsComponent', () => {
  let component: MemberBasicDetailsComponent;
  let fixture: ComponentFixture<MemberBasicDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MemberBasicDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberBasicDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
