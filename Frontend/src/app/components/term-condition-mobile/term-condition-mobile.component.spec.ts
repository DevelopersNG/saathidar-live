import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TermConditionMobileComponent } from './term-condition-mobile.component';

describe('TermConditionMobileComponent', () => {
  let component: TermConditionMobileComponent;
  let fixture: ComponentFixture<TermConditionMobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TermConditionMobileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TermConditionMobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
