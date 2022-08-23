import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdgradeHeaderComponent } from './updgrade-header.component';

describe('UpdgradeHeaderComponent', () => {
  let component: UpdgradeHeaderComponent;
  let fixture: ComponentFixture<UpdgradeHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdgradeHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdgradeHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
