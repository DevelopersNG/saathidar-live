import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdgradeFooterComponent } from './updgrade-footer.component';

describe('UpdgradeFooterComponent', () => {
  let component: UpdgradeFooterComponent;
  let fixture: ComponentFixture<UpdgradeFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdgradeFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdgradeFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
