import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersearchHeaderComponent } from './membersearch-header.component';

describe('MembersearchHeaderComponent', () => {
  let component: MembersearchHeaderComponent;
  let fixture: ComponentFixture<MembersearchHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MembersearchHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MembersearchHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
