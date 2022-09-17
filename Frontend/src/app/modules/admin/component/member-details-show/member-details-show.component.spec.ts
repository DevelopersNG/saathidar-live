import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberDetailsShowComponent } from './member-details-show.component';

describe('MemberDetailsShowComponent', () => {
  let component: MemberDetailsShowComponent;
  let fixture: ComponentFixture<MemberDetailsShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MemberDetailsShowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberDetailsShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
