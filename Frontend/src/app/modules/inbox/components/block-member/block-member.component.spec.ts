import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockMemberComponent } from './block-member.component';

describe('BlockMemberComponent', () => {
  let component: BlockMemberComponent;
  let fixture: ComponentFixture<BlockMemberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlockMemberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
