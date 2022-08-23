import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterSearchComponent } from './footer-search.component';

describe('FooterSearchComponent', () => {
  let component: FooterSearchComponent;
  let fixture: ComponentFixture<FooterSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
