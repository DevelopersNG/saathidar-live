import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefineSearchComponent } from './refine-search.component';

describe('RefineSearchComponent', () => {
  let component: RefineSearchComponent;
  let fixture: ComponentFixture<RefineSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefineSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefineSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
