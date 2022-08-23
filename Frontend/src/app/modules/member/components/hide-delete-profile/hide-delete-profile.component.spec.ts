import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HideDeleteProfileComponent } from './hide-delete-profile.component';

describe('HideDeleteProfileComponent', () => {
  let component: HideDeleteProfileComponent;
  let fixture: ComponentFixture<HideDeleteProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HideDeleteProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HideDeleteProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
