import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TermsConComponent } from './terms-con.component';

describe('TermsConComponent', () => {
  let component: TermsConComponent;
  let fixture: ComponentFixture<TermsConComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TermsConComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TermsConComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
