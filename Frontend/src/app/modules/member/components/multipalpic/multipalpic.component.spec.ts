import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipalpicComponent } from './multipalpic.component';

describe('MultipalpicComponent', () => {
  let component: MultipalpicComponent;
  let fixture: ComponentFixture<MultipalpicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultipalpicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MultipalpicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
