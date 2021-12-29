import { TestBed } from '@angular/core/testing';

import { VacancySearchService } from './vacancy-search.service';

describe('FilterService', () => {
  let service: VacancySearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacancySearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
