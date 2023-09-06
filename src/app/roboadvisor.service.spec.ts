import { TestBed } from '@angular/core/testing';

import { RoboadvisorService } from './roboadvisor.service';

describe('RoboadvisorService', () => {
  let service: RoboadvisorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoboadvisorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
