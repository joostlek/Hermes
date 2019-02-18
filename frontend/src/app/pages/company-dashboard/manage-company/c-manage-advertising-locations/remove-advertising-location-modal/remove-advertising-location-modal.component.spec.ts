import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RemoveAdvertisingLocationModalComponent} from './remove-advertising-location-modal.component';

describe('RemoveAdvertisingLocationModalComponent', () => {
    let component: RemoveAdvertisingLocationModalComponent;
    let fixture: ComponentFixture<RemoveAdvertisingLocationModalComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [RemoveAdvertisingLocationModalComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RemoveAdvertisingLocationModalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
