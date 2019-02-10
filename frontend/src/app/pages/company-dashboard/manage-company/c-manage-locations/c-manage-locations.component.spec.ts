import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CManageLocationsComponent} from './c-manage-locations.component';

describe('CManageLocationsComponent', () => {
    let component: CManageLocationsComponent;
    let fixture: ComponentFixture<CManageLocationsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CManageLocationsComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CManageLocationsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
