import { TestBed } from '@angular/core/testing';

import { CodigoCapchaService } from './codigo-capcha.service';

describe('CodigoCapchaService', () => {
  let service: CodigoCapchaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CodigoCapchaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
