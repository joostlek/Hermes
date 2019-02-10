import {TestBed} from '@angular/core/testing';

import {ChosenCompanyService} from './chosen-company.service';

describe('ChosenCompanyService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: ChosenCompanyService = TestBed.get(ChosenCompanyService);
        expect(service).toBeTruthy();
    });
});
