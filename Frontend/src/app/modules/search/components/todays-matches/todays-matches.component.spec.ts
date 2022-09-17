import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodaysMatchesComponent } from './todays-matches.component';

describe('TodaysMatchesComponent', () => {
  let component: TodaysMatchesComponent;
  let fixture: ComponentFixture<TodaysMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodaysMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodaysMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
