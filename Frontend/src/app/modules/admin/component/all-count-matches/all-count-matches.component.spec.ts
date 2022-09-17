import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCountMatchesComponent } from './all-count-matches.component';

describe('AllCountMatchesComponent', () => {
  let component: AllCountMatchesComponent;
  let fixture: ComponentFixture<AllCountMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCountMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCountMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
