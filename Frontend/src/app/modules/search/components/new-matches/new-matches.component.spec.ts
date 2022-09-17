import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMatchesComponent } from './new-matches.component';

describe('NewMatchesComponent', () => {
  let component: NewMatchesComponent;
  let fixture: ComponentFixture<NewMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
