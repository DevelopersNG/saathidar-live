import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpMobileComponent } from './help-mobile.component';

describe('HelpMobileComponent', () => {
  let component: HelpMobileComponent;
  let fixture: ComponentFixture<HelpMobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelpMobileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HelpMobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
