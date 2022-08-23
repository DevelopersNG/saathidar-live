import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlansDetailsComponent } from './plans-details.component';

describe('PlansDetailsComponent', () => {
  let component: PlansDetailsComponent;
  let fixture: ComponentFixture<PlansDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlansDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlansDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
