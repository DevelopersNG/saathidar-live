import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdgradeNowComponent } from './updgrade-now.component';

describe('UpdgradeNowComponent', () => {
  let component: UpdgradeNowComponent;
  let fixture: ComponentFixture<UpdgradeNowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdgradeNowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdgradeNowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
