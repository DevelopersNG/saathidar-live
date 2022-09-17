import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderSerachComponent } from './header-serach.component';

describe('HeaderSerachComponent', () => {
  let component: HeaderSerachComponent;
  let fixture: ComponentFixture<HeaderSerachComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderSerachComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderSerachComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
