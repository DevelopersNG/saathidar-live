import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePremiumComponent } from './update-premium.component';

describe('UpdatePremiumComponent', () => {
  let component: UpdatePremiumComponent;
  let fixture: ComponentFixture<UpdatePremiumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatePremiumComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePremiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
