import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CManageAdvertisingLocationsComponent} from './c-manage-advertising-locations.component';

describe('CManageAdvertisingLocationsComponent', () => {
    let component: CManageAdvertisingLocationsComponent;
    let fixture: ComponentFixture<CManageAdvertisingLocationsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CManageAdvertisingLocationsComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CManageAdvertisingLocationsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
