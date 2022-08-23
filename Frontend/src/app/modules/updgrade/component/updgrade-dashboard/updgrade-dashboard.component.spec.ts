import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdgradeDashboardComponent } from './updgrade-dashboard.component';

describe('UpdgradeDashboardComponent', () => {
  let component: UpdgradeDashboardComponent;
  let fixture: ComponentFixture<UpdgradeDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdgradeDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdgradeDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
