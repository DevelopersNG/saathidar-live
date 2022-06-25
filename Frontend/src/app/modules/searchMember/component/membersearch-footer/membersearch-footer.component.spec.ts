import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersearchFooterComponent } from './membersearch-footer.component';

describe('MembersearchFooterComponent', () => {
  let component: MembersearchFooterComponent;
  let fixture: ComponentFixture<MembersearchFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MembersearchFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MembersearchFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
