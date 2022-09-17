import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdgradeComponent } from './updgrade.component';

describe('UpdgradeComponent', () => {
  let component: UpdgradeComponent;
  let fixture: ComponentFixture<UpdgradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdgradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdgradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
