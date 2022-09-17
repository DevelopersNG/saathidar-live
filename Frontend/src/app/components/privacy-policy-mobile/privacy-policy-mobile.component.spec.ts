import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivacyPolicyMobileComponent } from './privacy-policy-mobile.component';

describe('PrivacyPolicyMobileComponent', () => {
  let component: PrivacyPolicyMobileComponent;
  let fixture: ComponentFixture<PrivacyPolicyMobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrivacyPolicyMobileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivacyPolicyMobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
