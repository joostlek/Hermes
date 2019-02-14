import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AddAdvertisingLocationModalComponent} from './add-advertising-location-modal.component';

describe('AddAdvertisingLocationModalComponent', () => {
    let component: AddAdvertisingLocationModalComponent;
    let fixture: ComponentFixture<AddAdvertisingLocationModalComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [AddAdvertisingLocationModalComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AddAdvertisingLocationModalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
