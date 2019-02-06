import {TestBed} from '@angular/core/testing';

import {ChosenLocationService} from './chosen-location.service';

describe('ChosenLocationService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: ChosenLocationService = TestBed.get(ChosenLocationService);
        expect(service).toBeTruthy();
    });
});
