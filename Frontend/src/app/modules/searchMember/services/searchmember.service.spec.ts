import { TestBed } from '@angular/core/testing';

import { SearchmemberService } from './searchmember.service';

describe('SearchmemberService', () => {
  let service: SearchmemberService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchmemberService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
