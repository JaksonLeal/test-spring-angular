import { TestBed } from '@angular/core/testing';

import { AdminsysGuard } from './adminsys.guard';

describe('AdminsysGuard', () => {
  let guard: AdminsysGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminsysGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
