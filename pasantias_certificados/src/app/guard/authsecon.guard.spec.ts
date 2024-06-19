import { TestBed } from '@angular/core/testing';
import { CanActivateChildFn } from '@angular/router';

import { authseconGuard } from './authsecon.guard';

describe('authseconGuard', () => {
  const executeGuard: CanActivateChildFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authseconGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
