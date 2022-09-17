import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PremiumToLoginComponent } from './premium-to-login.component';

describe('PremiumToLoginComponent', () => {
  let component: PremiumToLoginComponent;
  let fixture: ComponentFixture<PremiumToLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PremiumToLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PremiumToLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
